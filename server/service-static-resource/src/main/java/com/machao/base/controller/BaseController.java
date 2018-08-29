package com.machao.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

import com.machao.base.config.BurglarChainConfig;
import com.machao.base.model.exception.RequestParamsErrorException;
import com.machao.base.model.exception.UnauthorizedException;

public class BaseController {
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	@Autowired
	private BurglarChainConfig burglarChainConfig;
	
	protected void checkRequestParams(Errors errors) {
		if(errors.hasErrors()) {
			throw new RequestParamsErrorException();
		}
	}
	
	protected void checkBurglarChain(HttpServletRequest request) {
		if(!burglarChainConfig.isEnable()) return;
		String referer = request.getHeader("referer");
		if(referer == null || burglarChainConfig.getReferers().contains(referer)) return;
		
		logger.error("reject unknow referer: {}", referer);
		throw new UnauthorizedException();
	}
	
}
