package com.machao.base.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.machao.base.model.exception.ResourceNotFoundException;
import com.machao.base.model.mq.audio.request.AudioPlayListRequest;
import com.machao.base.model.mq.audio.response.AudioPlayListResponse;
import com.machao.base.model.mq.image.request.ImageResizingRequest;
import com.machao.base.model.mq.image.response.ImageResizingResponse;
import com.machao.base.model.mq.video.request.VideoPlayListRequest;
import com.machao.base.model.mq.video.response.VideoPlayListResponse;
import com.machao.base.model.persit.StaticResource;
import com.machao.base.model.persit.StaticResource.Type;
import com.machao.base.service.AudioMessageService;
import com.machao.base.service.ImageMessageService;
import com.machao.base.service.StaticResourceService;
import com.machao.base.service.VideoMessageService;

import io.swagger.annotations.ApiOperation;

@RestController
@RestControllerAdvice 
@RequestMapping("/static-resource")
public class StaticResourceFileController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(StaticResourceFileController.class);
	
	@Autowired
	private StaticResourceService staticResourceService;
	
	@Autowired
	private ImageMessageService imageMessageService;
	@Autowired
	private AudioMessageService audioMessageService;
	@Autowired
	private VideoMessageService videoMessageService;
	
	@ApiOperation("file image")
	@GetMapping("/file/image/{id}")
	public void image(@PathVariable String id, 
			@RequestParam(name="w", defaultValue="0") int w, 
			@RequestParam(name="h", defaultValue="0") int h, 
			HttpServletResponse response) {
		StaticResource staticResource = staticResourceService.findById(id).orElseThrow(ResourceNotFoundException::new);
		if(!Type.image.equals(staticResource.getType())) return;
		this.checkStaticResourceAuthorize(staticResource);

		try {
			File srcFile = new File(staticResource.getPath());
			if(!srcFile.exists()) throw new ResourceNotFoundException();
			ImageResizingResponse imageResizingResponse = imageMessageService.resizing(new ImageResizingRequest(staticResource, w <= 0 ? 0 : w, h <= 0 ? 0 : h));
			
			logger.debug("redirect to {}, for file: {}", imageResizingResponse.getUrl(), srcFile);
			
			response.sendRedirect(imageResizingResponse.getUrl());
		} catch (IOException e) {
			logger.error("error to handle image: {}", staticResource.getPath());
		}
	}
	
	@ApiOperation("file audio")
	@GetMapping("/file/audio/{id}")
	public void audio(@PathVariable String id, HttpServletResponse response) {
		StaticResource staticResource = staticResourceService.findById(id).orElseThrow(ResourceNotFoundException::new);
		if(!Type.audio.equals(staticResource.getType())) return;
		this.checkStaticResourceAuthorize(staticResource);

		try {
			File file = new File(staticResource.getPath());
			if(!file.exists()) throw new ResourceNotFoundException();
			
			AudioPlayListResponse audioPlayListResponse = audioMessageService.palylist(new AudioPlayListRequest(staticResource));
			if(audioPlayListResponse == null)  throw new ResourceNotFoundException();
			
			logger.debug("redirect to {}, for file: {}", audioPlayListResponse.getUrl(), file);
			
			response.sendRedirect(audioPlayListResponse.getUrl());
		} catch (IOException e) {
			logger.error("error to handle audio: {}", staticResource.getPath());
		}
	}

	@ApiOperation("file video")
	@GetMapping("/file/video/{id}")
	public void video(@PathVariable String id, HttpServletResponse response) {
		StaticResource staticResource = staticResourceService.findById(id).orElseThrow(ResourceNotFoundException::new);
		if(!Type.video.equals(staticResource.getType())) return;
		this.checkStaticResourceAuthorize(staticResource);

		try {
			File file = new File(staticResource.getPath());
			if(!file.exists()) throw new ResourceNotFoundException();
			VideoPlayListResponse videoPlayListResponse = videoMessageService.palylist(new VideoPlayListRequest(staticResource));
			if(videoPlayListResponse == null)  throw new ResourceNotFoundException();
			
			logger.debug("redirect to {}, for file: {}", videoPlayListResponse.getUrl(), file);
			
			response.sendRedirect(videoPlayListResponse.getUrl());
		} catch (IOException e) {
			logger.error("error to handle audio: {}", staticResource.getPath());
		}
	}
}
