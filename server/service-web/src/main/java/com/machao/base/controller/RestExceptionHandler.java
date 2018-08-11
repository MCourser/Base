package com.machao.base.controller;

import java.nio.file.AccessDeniedException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.machao.base.exception.MysqlException;
import com.machao.base.exception.RequestParamsErrorException;
import com.machao.base.exception.ResourceNotFoundException;
import com.machao.base.exception.StaticResourceDeleteErrorException;
import com.machao.base.exception.UnauthorizedException;
import com.machao.base.exception.UserNotFoundException;
import com.machao.base.model.ExceptionReponse;

@ControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler({
		DataIntegrityViolationException.class, 
		ResourceNotFoundException.class,
		AccessDeniedException.class,
		RequestParamsErrorException.class,
		MysqlException.class,
		UserNotFoundException.class,
		UnauthorizedException.class,
		StaticResourceDeleteErrorException.class
	})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ResponseEntity<ExceptionReponse> dataIntegrityViolationException(Exception e) {
		return ResponseEntity.badRequest().body(new ExceptionReponse(e.getClass().getName()));
	}   
}