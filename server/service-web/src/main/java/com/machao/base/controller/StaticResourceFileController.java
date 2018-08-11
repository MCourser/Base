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

import com.machao.base.exception.ResourceNotFoundException;
import com.machao.base.model.persit.StaticResource;
import com.machao.base.model.persit.StaticResource.Type;
import com.machao.base.mq.static_resource.audio.request.AudioPlayListRequest;
import com.machao.base.mq.static_resource.audio.response.AudioPlayListResponse;
import com.machao.base.mq.static_resource.image.request.ImageResizingRequest;
import com.machao.base.mq.static_resource.image.response.ImageResizingResponse;
import com.machao.base.mq.static_resource.video.request.VideoPlayListRequest;
import com.machao.base.mq.static_resource.video.response.VideoPlayListResponse;
import com.machao.base.service.AudioService;
import com.machao.base.service.ImageService;
import com.machao.base.service.StaticResourceService;
import com.machao.base.service.VideoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RestControllerAdvice 
@RequestMapping("/static-resource")
public class StaticResourceFileController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(StaticResourceFileController.class);
	
	@Autowired
	private StaticResourceService staticResourceService;
	
	@Autowired
	private ImageService imageService;
	@Autowired
	private AudioService audioService;
	@Autowired
	private VideoService videoService;
	
	@ApiOperation("file image")
	@GetMapping("/file/image/{id}")
	public void image(@PathVariable String id, 
			@RequestParam(name="w", required=false) Integer w, 
			@RequestParam(name="h", required=false) Integer h, 
			HttpServletResponse response) {
		StaticResource staticResource = staticResourceService.findById(id).orElseThrow(ResourceNotFoundException::new);
		if(!Type.image.equals(staticResource.getType())) return;
		super.checkStaticResourceAuthorize(staticResource);

		try {
			File srcFile = new File(staticResource.getPath());
			if(!srcFile.exists()) throw new ResourceNotFoundException();
			ImageResizingResponse imageResizingResponse = imageService.resizing(new ImageResizingRequest(srcFile, w==null?0:w, h==null?0:h, staticResource.getContentType()));
			File dstFile = imageResizingResponse.getFile();
			
			logger.debug("redirect to {}, for file: {}", imageResizingResponse.getUrl(), dstFile.getAbsolutePath());
			
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
		super.checkStaticResourceAuthorize(staticResource);

		try {
			File file = new File(staticResource.getPath());
			if(!file.exists()) throw new ResourceNotFoundException();
			AudioPlayListResponse audioPlayListResponse = audioService.palylist(new AudioPlayListRequest(file));
			if(audioPlayListResponse == null)  throw new ResourceNotFoundException();
			
			logger.debug("redirect to {}, for file: {}", audioPlayListResponse.getUrl(), file.getAbsolutePath());
			
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
		super.checkStaticResourceAuthorize(staticResource);

		try {
			File file = new File(staticResource.getPath());
			if(!file.exists()) throw new ResourceNotFoundException();
			VideoPlayListResponse videoPlayListResponse = videoService.palylist(new VideoPlayListRequest(file));
			if(videoPlayListResponse == null)  throw new ResourceNotFoundException();
			
			logger.debug("redirect to {}, for file: {}", videoPlayListResponse.getUrl(), file.getAbsolutePath());
			
			response.sendRedirect(videoPlayListResponse.getUrl());
		} catch (IOException e) {
			logger.error("error to handle audio: {}", staticResource.getPath());
		}
	}
	
}
