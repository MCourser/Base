package com.machao.base.statis_resource.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.machao.base.mq.static_resource.image.response.ImageResizingResponse;
import com.machao.base.statis_resource.exception.ResourceNotFoundException;
import com.machao.base.statis_resource.utils.StaticResourcePathUtils;

@RestController
@RestControllerAdvice 
public class StaticResourceImageController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(StaticResourceImageController.class);
	
	@Autowired
	private StaticResourcePathUtils staticResourcePathUtils;

	@GetMapping("/image/{uuid}")
	public void image(@PathVariable String uuid, HttpServletRequest request, HttpServletResponse response) {
		super.checkBurglarChain(request);
		
		ImageResizingResponse imageResizingResponse = staticResourcePathUtils.getAttribute(uuid, ImageResizingResponse.class);
		if(!imageResizingResponse.getFile().exists()) throw new ResourceNotFoundException();
		
		try {
			byte[] bytes = FileUtils.readFileToByteArray(imageResizingResponse.getFile());
			response.setContentType(imageResizingResponse.getContentType());
			response.getOutputStream().write(bytes);
		} catch (IOException e) {
			logger.error("file: {} send error", imageResizingResponse.getFile().getAbsolutePath());
		}
	}
}
	
