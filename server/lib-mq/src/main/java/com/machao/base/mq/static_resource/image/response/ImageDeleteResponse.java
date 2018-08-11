package com.machao.base.mq.static_resource.image.response;

import java.io.Serializable;

public class ImageDeleteResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean success;

	public ImageDeleteResponse() {
		super();
	}

	public ImageDeleteResponse(boolean success) {
		super();
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
