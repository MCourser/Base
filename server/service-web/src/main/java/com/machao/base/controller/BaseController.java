package com.machao.base.controller;

import org.springframework.validation.Errors;

import com.machao.base.exception.RequestParamsErrorException;

public class BaseController {
	
	protected void chcekRequestPrams(Errors errors) {
		if(errors.hasErrors()) {
			throw new RequestParamsErrorException();
		}
	}
	
}
