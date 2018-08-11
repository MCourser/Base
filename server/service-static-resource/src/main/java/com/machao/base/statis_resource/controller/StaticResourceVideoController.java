package com.machao.base.statis_resource.controller;

import java.io.File;
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

import com.machao.base.mq.static_resource.video.response.VideoPlayListResponse;
import com.machao.base.statis_resource.exception.ResourceNotFoundException;
import com.machao.base.statis_resource.utils.StaticResourcePathUtils;

@RestController
@RestControllerAdvice 
public class StaticResourceVideoController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(StaticResourceVideoController.class);
	
	@Autowired
	private StaticResourcePathUtils staticResourcePathUtils;

	@GetMapping("/video/{uuid}/")
	public void videoM3u8(@PathVariable String uuid, HttpServletRequest request, HttpServletResponse response) {
		super.checkBurglarChain(request);
		
		VideoPlayListResponse videoPlayListResponse = staticResourcePathUtils.getAttribute(uuid, VideoPlayListResponse.class);
		if(!videoPlayListResponse.getM3u8File().exists()) throw new ResourceNotFoundException();
		
		try {
			String m3u8Content = staticResourcePathUtils.bindPath4VideoPlayListM3u8File(uuid, videoPlayListResponse.getM3u8File());
			response.setContentType(VideoPlayListResponse.M3U8_CONTENT_TYPE);
			response.getOutputStream().write(m3u8Content.getBytes());
		} catch (IOException e) {
			logger.error("file: {} send error", videoPlayListResponse.getM3u8File().getAbsolutePath());
		}
	}
	
	@GetMapping("/video/{uuid}/{palylist}")
	public void videoTs(@PathVariable String uuid, @PathVariable String palylist, HttpServletRequest request,  HttpServletResponse response) {
		super.checkBurglarChain(request);
		
		VideoPlayListResponse videoPlayListResponse = staticResourcePathUtils.getAttribute(uuid, VideoPlayListResponse.class);
		if(!videoPlayListResponse.getM3u8File().exists()) throw new ResourceNotFoundException();
		
		try {
			for(File tsFile : videoPlayListResponse.getTsFiles()) {
				if(tsFile.getName().equalsIgnoreCase(palylist)) {
					byte[] bytes = FileUtils.readFileToByteArray(tsFile);
					response.setContentType(VideoPlayListResponse.TS_CONTENT_TYPE);
					response.getOutputStream().write(bytes);
					break;
				}
			}
		} catch (IOException e) {
			logger.error("file: {} send error for ts file: {}", videoPlayListResponse.getM3u8File().getAbsolutePath(), palylist);
		}
	}
}
	
