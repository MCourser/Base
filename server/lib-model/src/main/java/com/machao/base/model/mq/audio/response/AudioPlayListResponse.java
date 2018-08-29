package com.machao.base.model.mq.audio.response;

import java.io.Serializable;

public class AudioPlayListResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String M3U8_CONTENT_TYPE = "application/vnd.apple.mpegurl";
	public static final String TS_CONTENT_TYPE = "audio/mp2t";

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
