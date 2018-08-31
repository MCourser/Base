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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.machao.base.model.exception.RequestParamsErrorException;
import com.machao.base.model.exception.ResourceNotFoundException;
import com.machao.base.model.exception.ResourceNotReadyException;
import com.machao.base.model.persit.StaticResource;
import com.machao.base.model.persit.StaticResource.Type;
import com.machao.base.service.ImageService;
import com.machao.base.service.StaticResourceService;

@RestController
@RestControllerAdvice 
public class StaticResourceImageController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(StaticResourceImageController.class);

	@Autowired
	private StaticResourceService staticResourceService;

	@GetMapping("/image/{uuid}")
	public void image(@PathVariable String uuid, @RequestParam(name="w") int width, @RequestParam(name="h") int height, HttpServletRequest request, HttpServletResponse response) {
		super.checkBurglarChain(request);
		
		StaticResource staticResource = staticResourceService.findById(uuid).orElseThrow(ResourceNotFoundException::new);
		if(!staticResource.isHandled()) throw new ResourceNotReadyException();
		if(!Type.image.equals(staticResource.getType())) throw new RequestParamsErrorException();
		
		File file = ImageService.obtainFile(new File(staticResource.getPath()), width, height);
		if(!file.exists()) throw new ResourceNotFoundException();
		
		try {
			response.setContentType(staticResource.getContentType());
			response.getOutputStream().write(FileUtils.readFileToByteArray(file));
		} catch (IOException e) {
			logger.error("file: {} send error", file);
		}
	}
}
	
