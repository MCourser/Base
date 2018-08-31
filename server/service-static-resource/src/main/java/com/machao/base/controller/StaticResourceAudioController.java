package com.machao.base.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.machao.base.model.exception.RequestParamsErrorException;
import com.machao.base.model.exception.ResourceNotFoundException;
import com.machao.base.model.exception.ResourceNotReadyException;
import com.machao.base.model.mq.audio.response.AudioPlayListResponse;
import com.machao.base.model.persit.StaticResource;
import com.machao.base.model.persit.StaticResource.Type;
import com.machao.base.service.StaticResourceService;
import com.machao.base.utils.StaticResourcePathUtils;

@RestController
@RestControllerAdvice 
public class StaticResourceAudioController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(StaticResourceAudioController.class);
	
	@Autowired
	private StaticResourceService staticResourceService;
	
	@Autowired
	private StaticResourcePathUtils staticResourcePathUtils;

	@GetMapping("/audio/{uuid}/")
	public void audioM3u8(@PathVariable String uuid, HttpServletRequest request, HttpServletResponse response) {
		super.checkBurglarChain(request);
		
		StaticResource staticResource = staticResourceService.findById(uuid).orElseThrow(ResourceNotFoundException::new);
		if(!Type.audio.equals(staticResource.getType())) throw new RequestParamsErrorException();
		if(!staticResource.isHandled()) throw new IllegalStateException();
		
		try {
			String m3u8Content = staticResourcePathUtils.obtainAudioM3u8FileContent(staticResource);
			if(StringUtils.isEmpty(m3u8Content)) throw new IllegalStateException();
			
			response.setContentType(AudioPlayListResponse.M3U8_CONTENT_TYPE);
			response.getOutputStream().write(m3u8Content.getBytes());
		} catch (IOException e) {
			logger.error("file: {} send error", staticResource.getPath());
		}
	}
	
	@GetMapping("/audio/{uuid}/{palylist}")
	public void audioTs(@PathVariable String uuid, @PathVariable String palylist, HttpServletRequest request,  HttpServletResponse response) {
		super.checkBurglarChain(request);
		
		StaticResource staticResource = staticResourceService.findById(uuid).orElseThrow(ResourceNotFoundException::new);
		if(!staticResource.isHandled()) throw new ResourceNotReadyException();
		if(!Type.audio.equals(staticResource.getType())) throw new RequestParamsErrorException();
		if(!staticResource.isHandled()) throw new IllegalStateException();
		
		File file = new File(staticResource.getPath());
		if(!file.exists()) throw new IllegalStateException();
		File tsFile = new File(file.getParent(), palylist);
		if(!tsFile.exists()) throw new IllegalStateException();
		
		try {
			response.setContentType(AudioPlayListResponse.TS_CONTENT_TYPE);
			response.getOutputStream().write(FileUtils.readFileToByteArray(tsFile));
		} catch (IOException e) {
			logger.error("file: {} send error for ts file: {}", file.getAbsolutePath(), palylist);
		}
	}
}
	
