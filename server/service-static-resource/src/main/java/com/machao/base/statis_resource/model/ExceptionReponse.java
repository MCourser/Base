package com.machao.base.statis_resource.model;

import java.io.Serializable;

public class ExceptionReponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public ExceptionReponse() {
		super();
	}

	public ExceptionReponse(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
