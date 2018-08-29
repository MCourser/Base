package com.machao.base.model.mq.video.response;

import java.io.Serializable;

public class VideoPlayListResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String M3U8_CONTENT_TYPE = "application/vnd.apple.mpegurl";
	public static final String TS_CONTENT_TYPE = "video/mp2t";

	private String url;
	
	public VideoPlayListResponse() {
		super();
	}

	public VideoPlayListResponse(String url) {
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
