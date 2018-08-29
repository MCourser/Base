package com.machao.base.model.mq.image.response;

import java.io.Serializable;

public class ImageResizingResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private String url;

	public ImageResizingResponse() {
		super();
	}

	public ImageResizingResponse(String url) {
		super();
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}