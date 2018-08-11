package com.machao.base.mq.static_resource.audio.response;

import java.io.Serializable;

public class AudioDeleteResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean success;

	public AudioDeleteResponse() {
		super();
	}

	public AudioDeleteResponse(boolean success) {
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
