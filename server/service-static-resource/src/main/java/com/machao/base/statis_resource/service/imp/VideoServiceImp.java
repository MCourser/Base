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
import com.machao.base.mq.static_resource.video.request.VideoConvertRequest;
import com.machao.base.mq.static_resource.video.request.VideoDeleteRequest;
import com.machao.base.mq.static_resource.video.request.VideoPlayListRequest;
import com.machao.base.mq.static_resource.video.response.VideoDeleteResponse;
import com.machao.base.mq.static_resource.video.response.VideoPlayListResponse;
import com.machao.base.static_resource.ffmpeg.FFmpegHandler.Type;
import com.machao.base.static_resource.handler.video.imp.FFmpegVideoHandler;
import com.machao.base.statis_resource.service.VideoService;
import com.machao.base.statis_resource.utils.StaticResourcePathUtils;

@Service
public class VideoServiceImp implements VideoService{
	private static final Logger logger = LoggerFactory.getLogger(VideoServiceImp.class);

	@Autowired
	private StaticResourcePathUtils staticResourcePathUtils;
	
	@Autowired
	private FFmpegVideoHandler ffmpegVideoHandler;

	@RabbitListener(queues = QueueName.VideoConvert)
	@Override
	public void convert(VideoConvertRequest videoConvertRequest) {
		try {
			this.ffmpegVideoHandler.handle(videoConvertRequest.getFile());
		} catch (Exception e) {
			logger.error("error to convert file {} to m3u8, exception: {}", videoConvertRequest.getFile().getAbsolutePath(), e.getMessage());
		} 
	}

	@RabbitListener(queues = QueueName.VideoDelete)
	@Override
	public VideoDeleteResponse handle(VideoDeleteRequest videoDeleteRequest) {
		try {
			File file = videoDeleteRequest.getFile();
			FileUtils.deleteDirectory(file.getParentFile());
			return new VideoDeleteResponse(true);
		} catch (IOException e) {
			logger.error("error to delete file {} to m3u8, exception: {}", videoDeleteRequest.getFile().getAbsolutePath(), e.getMessage());
			return new VideoDeleteResponse(false);
		}
	}
	
	@RabbitListener(queues = QueueName.VideoPlayList)
	@Override
	public VideoPlayListResponse handle(VideoPlayListRequest videoPlayListRequest) {
		VideoPlayListResponse VideoPlayListResponse = new VideoPlayListResponse();
		
		try {
			File file = videoPlayListRequest.getFile();
			if(ffmpegVideoHandler.isLocked(file)) return null;
			
			for(File subFile : file.getParentFile().listFiles()) {
				String subFileName = subFile.getName();
				if(subFileName.endsWith(Type.m3u8.toString())) {
					VideoPlayListResponse.setM3u8File(subFile);
				} else if(subFileName.endsWith(Type.ts.toString())) {
					VideoPlayListResponse.addTsFile(subFile);
				}
			}
			
			this.staticResourcePathUtils.bindPath4VideoPlayListResponse(VideoPlayListResponse);

			logger.debug("generate video url: {} for file: {}", VideoPlayListResponse.getUrl(), videoPlayListRequest.getFile().getAbsolutePath());
		} catch (Exception e) {
			logger.debug("error to generate video url for file: {}, exception: {}", videoPlayListRequest.getFile().getAbsolutePath(), e.getMessage());
		}
		
		return VideoPlayListResponse;
	}
	
}
