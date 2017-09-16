package com.machao.sp.exception;

public class RequestParamsErrorException extends RuntimeException{
	private static final long serialVersionUID = 2722989932819775292L;

	public RequestParamsErrorException() {
		super("Request Prams Error");
	}
}
