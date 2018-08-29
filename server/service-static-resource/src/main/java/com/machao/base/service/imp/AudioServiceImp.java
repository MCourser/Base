package com.machao.base.service.imp;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.machao.base.handler.audio.imp.FFmpegAutioHandler;
import com.machao.base.model.mq.QueueName;
import com.machao.base.model.mq.audio.request.AudioConvertRequest;
import com.machao.base.model.mq.audio.request.AudioDeleteRequest;
import com.machao.base.model.mq.audio.request.AudioPlayListRequest;
import com.machao.base.model.mq.audio.response.AudioDeleteResponse;
import com.machao.base.model.mq.audio.response.AudioPlayListResponse;
import com.machao.base.model.persit.StaticResource;
import com.machao.base.service.AudioService;
import com.machao.base.service.StaticResourceService;
import com.machao.base.utils.StaticResourcePathUtils;

@Service
public class AudioServiceImp implements AudioService{
	private static final Logger logger = LoggerFactory.getLogger(AudioServiceImp.class);

	@Autowired
	private StaticResourceService staticResourceService;
	
	@Autowired
	private StaticResourcePathUtils staticResourcePathUtils;
	
	@Autowired
	private FFmpegAutioHandler ffmpegAutioHandler;

	@RabbitListener(queues = QueueName.AudioConvert)
	@Override
	public void convert(AudioConvertRequest audioConvertRequest) {
		StaticResource staticResource = audioConvertRequest.getStaticResource();
		
		try {
			File file = new File(staticResource.getPath());
			this.ffmpegAutioHandler.handle(file);
			
			staticResource.setHandled(true);
			this.staticResourceService.update(staticResource);
		} catch (Exception e) {
			logger.error("error to convert file {} to m3u8, exception: {}", staticResource.getPath(), e.getMessage());
			
			staticResource.setHandled(false);
			this.staticResourceService.update(staticResource);
		} 
	}

	@RabbitListener(queues = QueueName.AudioDelete)
	@Override
	public AudioDeleteResponse handle(AudioDeleteRequest audioDeleteRequest) {
		StaticResource staticResource = audioDeleteRequest.getStaticResource();
		
		try {
			File file = new File(staticResource.getPath());
			FileUtils.deleteDirectory(file.getParentFile());
			return new AudioDeleteResponse(true);
		} catch (IOException e) {
			logger.error("error to delete file {} to m3u8, exception: {}", staticResource.getPath(), e.getMessage());
			return new AudioDeleteResponse(false);
		}
	}
	
	@RabbitListener(queues = QueueName.AudioPlayList)
	@Override
	public AudioPlayListResponse handle(AudioPlayListRequest audioPlayListRequest) {
		StaticResource staticResource = audioPlayListRequest.getStaticResource();
		return new AudioPlayListResponse(staticResourcePathUtils.audioUrl(staticResource));
	}
	
}
