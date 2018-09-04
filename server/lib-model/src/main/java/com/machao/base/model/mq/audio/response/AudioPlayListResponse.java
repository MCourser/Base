package com.machao.base.model.mq.audio.response;

import java.io.Serializable;

public class AudioPlayListResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String MPD_CONTENT_TYPE = "text/xml";
	public static final String M4S_CONTENT_TYPE = "video/mp4";

	private String url;
	
	public AudioPlayListResponse() {
		super();
	}
	
	public AudioPlayListResponse(String url) {
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
