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
import com.machao.base.mq.static_resource.image.request.ImageDeleteRequest;
import com.machao.base.mq.static_resource.image.request.ImageResizingRequest;
import com.machao.base.mq.static_resource.image.response.ImageDeleteResponse;
import com.machao.base.mq.static_resource.image.response.ImageResizingResponse;
import com.machao.base.static_resource.handler.image.imp.OpencvImageHandler;
import com.machao.base.statis_resource.service.ImageService;
import com.machao.base.statis_resource.utils.StaticResourcePathUtils;

@Service
public class ImageServiceImp implements ImageService {
	private static final Logger logger = LoggerFactory.getLogger(ImageServiceImp.class);

	@Autowired
	private OpencvImageHandler handler;
	
	@Autowired
	private StaticResourcePathUtils staticResourcePathUtils;
	
	@RabbitListener(queues = QueueName.ImageResizing)
	@Override
	public ImageResizingResponse handle(ImageResizingRequest imageResizingRequest) {
		File srcFile = imageResizingRequest.getFile();
		File destFile = this.handler.resize(imageResizingRequest.getFile(), imageResizingRequest.getWidth(), imageResizingRequest.getHeight(), (width, height)->{
			return new File(srcFile.getParent(), (width + "x" + height + "_" + srcFile.getName())).getAbsolutePath();
		});
		
		ImageResizingResponse imageResizingResponse = new ImageResizingResponse(destFile, imageResizingRequest.getContentType());
		this.staticResourcePathUtils.bindPath4ImageResizingResponse(imageResizingResponse);
		
		logger.debug("image resizing and generate url: {} for file: {}, context-type: {}", imageResizingResponse.getUrl(), imageResizingResponse.getFile().getAbsolutePath(), imageResizingResponse.getContentType());
		
		return imageResizingResponse;
	}

	@RabbitListener(queues = QueueName.ImageDelete)
	@Override
	public ImageDeleteResponse handle(ImageDeleteRequest imageDeleteRequest) {
		try {
			File file = imageDeleteRequest.getFile();
			FileUtils.deleteDirectory(file.getParentFile());
			return new ImageDeleteResponse(true);
		} catch (IOException e) {
			e.printStackTrace();
			return new ImageDeleteResponse(false);
		}
	}

}
