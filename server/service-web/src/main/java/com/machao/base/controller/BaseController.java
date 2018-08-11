package com.machao.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

import com.machao.base.exception.RequestParamsErrorException;
import com.machao.base.exception.UnauthorizedException;
import com.machao.base.exception.UserNotFoundException;
import com.machao.base.model.persit.StaticResource;
import com.machao.base.model.persit.User;
import com.machao.base.service.UserService;
import com.machao.base.utils.SecurityUtils;

public class BaseController {
	
	@Autowired
	private UserService userService;
	
	protected void checkRequestParams(Errors errors) {
		if(errors.hasErrors()) {
			throw new RequestParamsErrorException();
		}
	}
	
	protected User obtainCurrentUser() {
		return this.userService.findByName(SecurityUtils.getPrincipal().getUsername()).orElseThrow(UserNotFoundException::new);
	}
	
	
	protected void checkStaticResourceAuthorize(StaticResource staticResource) {
		try {
			org.springframework.security.core.userdetails.User user = SecurityUtils.getPrincipal();
			if(!user.getUsername().equals(staticResource.getUser().getName())) throw new UnauthorizedException();
		} catch (Exception e) {
			if(!staticResource.isPublic()) throw new UnauthorizedException();
		}
	}
}
