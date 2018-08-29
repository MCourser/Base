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
		ImageResizingResponse imageResizingResponse = new ImageResizingResponse();
		
		try {
			StaticResource staticResource = imageResizingRequest.getStaticResource();
			System.out.println(">>>>>" + staticResource);
			File srcFile = new File(staticResource.getPath());
			File destFile = this.handler.resize(srcFile, imageResizingRequest.getWidth(), imageResizingRequest.getHeight(), (width, height)->{
				return new File(srcFile.getParent(), (width + "x" + height + "_" + srcFile.getName())).getAbsolutePath();
			});
			
			imageResizingResponse.setFile(destFile);
			imageResizingResponse.setContentType(staticResource.getContentType());
			this.staticResourcePathUtils.bindPath4ImageResizingResponse(imageResizingResponse);
			
			logger.debug("image resizing and generate url: {} for file: {}, context-type: {}", imageResizingResponse.getUrl(), imageResizingResponse.getFile(), imageResizingResponse.getContentType());
		} catch (Exception e) {
			logger.error("error to resize image and generate url: {} for file: {}, context-type: {}, exception: {}", imageResizingResponse.getUrl(), imageResizingResponse.getFile(), imageResizingResponse.getContentType(), e.getMessage());
		}
		
		return imageResizingResponse;
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
