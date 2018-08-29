package com.machao.base.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.machao.base.model.exception.ExceptionReponse;
import com.machao.base.model.exception.RequestParamsErrorException;
import com.machao.base.model.exception.ResourceNotFoundException;
import com.machao.base.model.exception.UnauthorizedException;

@ControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler({
		ResourceNotFoundException.class,
		RequestParamsErrorException.class,
		UnauthorizedException.class
	})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ResponseEntity<ExceptionReponse> dataIntegrityViolationException(Exception e) {
		return ResponseEntity.badRequest().body(new ExceptionReponse(e.getClass().getName()));
	}   
}