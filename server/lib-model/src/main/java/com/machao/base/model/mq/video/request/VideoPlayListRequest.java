package com.machao.base.model.mq.video.request;

import java.io.Serializable;

import com.machao.base.model.persit.StaticResource;

public class VideoPlayListRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	private StaticResource staticResource;

	public VideoPlayListRequest() {
		super();
	}

	public VideoPlayListRequest(StaticResource staticResource) {
		super();
		this.staticResource = staticResource;
	}

	public StaticResource getStaticResource() {
		return staticResource;
	}

	public void setStaticResource(StaticResource staticResource) {
		this.staticResource = staticResource;
	}

}
