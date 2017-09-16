package com.machao.sp.controller;

import org.springframework.validation.Errors;

import com.machao.sp.exception.RequestParamsErrorException;

public class BaseController {
	
	protected void chcekRequestPrams(Errors errors) {
		if(errors.hasErrors()) {
			throw new RequestParamsErrorException();
		}
	}
	
}
