package com.machao.base.model.mq.audio.request;

import java.io.Serializable;

import com.machao.base.model.persit.StaticResource;

public class AudioConvertRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	private StaticResource staticResource;

	public AudioConvertRequest() {
		super();
	}

	public AudioConvertRequest(StaticResource staticResource) {
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
