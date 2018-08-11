package com.machao.base.statis_resource.service.imp;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.machao.base.mq.QueueName;
import com.machao.base.mq.static_resource.audio.request.AudioConvertRequest;
import com.machao.base.mq.static_resource.audio.request.AudioDeleteRequest;
import com.machao.base.mq.static_resource.audio.request.AudioPlayListRequest;
import com.machao.base.mq.static_resource.audio.response.AudioDeleteResponse;
import com.machao.base.mq.static_resource.audio.response.AudioPlayListResponse;
import com.machao.base.static_resource.ffmpeg.FFmpegHandler.Type;
import com.machao.base.static_resource.handler.audio.imp.FFmpegAutioHandler;
import com.machao.base.statis_resource.service.AudioService;
import com.machao.base.statis_resource.utils.StaticResourcePathUtils;

@Service
public class AudioServiceImp implements AudioService{
	private static final Logger logger = LoggerFactory.getLogger(AudioServiceImp.class);

	@Autowired
	private StaticResourcePathUtils staticResourcePathUtils;
	
	@Autowired
	private FFmpegAutioHandler ffmpegAutioHandler;

	@RabbitListener(queues = QueueName.AudioConvert)
	@Override
	public void convert(AudioConvertRequest audioConvertRequest) {
		try {
			ffmpegAutioHandler.handle(audioConvertRequest.getFile());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@RabbitListener(queues = QueueName.AudioDelete)
	@Override
	public AudioDeleteResponse handle(AudioDeleteRequest audioDeleteRequest) {
		try {
			File file = audioDeleteRequest.getFile();
			FileUtils.deleteDirectory(file.getParentFile());
			return new AudioDeleteResponse(true);
		} catch (IOException e) {
			e.printStackTrace();
			return new AudioDeleteResponse(false);
		}
	}
	
	@RabbitListener(queues = QueueName.AudioPlayList)
	@Override
	public AudioPlayListResponse handle(AudioPlayListRequest audioPlayListRequest) {
		AudioPlayListResponse audioPlayListResponse = new AudioPlayListResponse();
		
		File file = audioPlayListRequest.getFile();
		if(ffmpegAutioHandler.isLocked(file)) return null;
		
		for(File subFile : file.getParentFile().listFiles()) {
			String subFileName = subFile.getName();
			if(subFileName.endsWith(Type.m3u8.toString())) {
				audioPlayListResponse.setM3u8File(subFile);
			} else if(subFileName.endsWith(Type.ts.toString())) {
				audioPlayListResponse.addTsFile(subFile);
			}
		}
		
		this.staticResourcePathUtils.bindPath4AudioPlayListResponse(audioPlayListResponse);

		logger.debug("audio generate url: {} for file: {}", audioPlayListResponse.getUrl(), audioPlayListResponse.getM3u8File().getAbsolutePath());
		
		return audioPlayListResponse;
	}
	
}
