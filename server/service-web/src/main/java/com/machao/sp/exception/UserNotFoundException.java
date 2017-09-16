package com.machao.sp.exception;

public class UserNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 5153057049361100355L;

	public UserNotFoundException() {
		super("User Not Found");
	}
}
