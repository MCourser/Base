package com.machao.sp.exception;

public class ResourceNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 5153057049361100355L;

	public ResourceNotFoundException() {
		super("Request Resource Not Found");
	}
}
