package com.machao.base.controller;

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

import com.machao.base.model.exception.ResourceNotFoundException;
import com.machao.base.model.mq.audio.response.AudioPlayListResponse;
import com.machao.base.utils.StaticResourcePathUtils;

@RestController
@RestControllerAdvice 
public class StaticResourceAudioController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(StaticResourceAudioController.class);
	
	@Autowired
	private StaticResourcePathUtils staticResourcePathUtils;

	@GetMapping("/audio/{uuid}/")
	public void audioM3u8(@PathVariable String uuid, HttpServletRequest request, HttpServletResponse response) {
		super.checkBurglarChain(request);
		
		AudioPlayListResponse audioPlayListResponse = staticResourcePathUtils.getAttribute(uuid, AudioPlayListResponse.class);
		if(!audioPlayListResponse.getM3u8File().exists()) throw new ResourceNotFoundException();
		
		try {
			String m3u8Content = staticResourcePathUtils.bindPath4AudioPlayListM3u8File(uuid, audioPlayListResponse.getM3u8File());
			response.setContentType(AudioPlayListResponse.M3U8_CONTENT_TYPE);
			response.getOutputStream().write(m3u8Content.getBytes());
		} catch (IOException e) {
			logger.error("file: {} send error", audioPlayListResponse.getM3u8File().getAbsolutePath());
		}
	}
	
	@GetMapping("/audio/{uuid}/{palylist}")
	public void audioTs(@PathVariable String uuid, @PathVariable String palylist, HttpServletRequest request,  HttpServletResponse response) {
		super.checkBurglarChain(request);
		
		AudioPlayListResponse audioPlayListResponse = staticResourcePathUtils.getAttribute(uuid, AudioPlayListResponse.class);
		if(!audioPlayListResponse.getM3u8File().exists()) throw new ResourceNotFoundException();
		
		try {
			for(File tsFile : audioPlayListResponse.getTsFiles()) {
				if(tsFile.getName().equalsIgnoreCase(palylist)) {
					byte[] bytes = FileUtils.readFileToByteArray(tsFile);
					response.setContentType(AudioPlayListResponse.TS_CONTENT_TYPE);
					response.getOutputStream().write(bytes);
					break;
				}
			}
		} catch (IOException e) {
			logger.error("file: {} send error for ts file: {}", audioPlayListResponse.getM3u8File().getAbsolutePath(), palylist);
		}
	}
}
	
