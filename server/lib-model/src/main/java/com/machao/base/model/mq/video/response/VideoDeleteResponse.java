package com.machao.base.model.mq.video.response;

import java.io.Serializable;

public class VideoDeleteResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean success;
	
	public VideoDeleteResponse() {
		super();
	}

	public VideoDeleteResponse(boolean success) {
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
