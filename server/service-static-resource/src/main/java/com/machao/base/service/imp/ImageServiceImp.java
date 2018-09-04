package com.machao.base.service.imp;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.machao.base.handler.image.imp.OpencvImageHandler;
import com.machao.base.model.mq.QueueName;
import com.machao.base.model.mq.image.request.ImageDeleteRequest;
import com.machao.base.model.mq.image.request.ImageResizingRequest;
import com.machao.base.model.mq.image.response.ImageDeleteResponse;
import com.machao.base.model.mq.image.response.ImageResizingResponse;
import com.machao.base.model.persit.StaticResource;
import com.machao.base.service.ImageService;
import com.machao.base.utils.StaticResourcePathUtils;

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
		StaticResource staticResource = imageResizingRequest.getStaticResource();
		
		try {
			File srcFile = new File(staticResource.getPath());
			File destFile = this.handler.resize(srcFile, imageResizingRequest.getWidth(), imageResizingRequest.getHeight(), (width, height)->{
				imageResizingRequest.setWidth(width);
				imageResizingRequest.setHeight(height);
				return ImageService.obtainFile(srcFile, width, height).getAbsolutePath();
			});
			
			logger.debug("image resizing and generate url for file: {}", destFile);
			
			return new ImageResizingResponse(staticResourcePathUtils.imageUrl(staticResource, imageResizingRequest.getWidth(), imageResizingRequest.getHeight()));
		} catch (Exception e) {
			logger.error("error to resize image resizing and generate url for file: {}, using origin file, exception: {}", staticResource.getPath(), e.getMessage());
			return new ImageResizingResponse(staticResourcePathUtils.imageUrl(staticResource));
		}
	}

	@RabbitListener(queues = QueueName.ImageDelete)
	@Override
	public ImageDeleteResponse handle(ImageDeleteRequest imageDeleteRequest) {
		StaticResource staticResource = imageDeleteRequest.getStaticResource();
		
		try {
			File file = new File(staticResource.getPath());
			FileUtils.deleteDirectory(file.getParentFile());
			return new ImageDeleteResponse(true);
		} catch (IOException e) {
			logger.error("error to delete image: {}, exception: {}", staticResource.getPath(), e.getMessage());
			return new ImageDeleteResponse(false);
		}
	}

}
