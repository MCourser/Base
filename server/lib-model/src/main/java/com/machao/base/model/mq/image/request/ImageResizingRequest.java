package com.machao.base.model.mq.image.request;

import java.io.Serializable;

import com.machao.base.model.persit.StaticResource;

public class ImageResizingRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	private StaticResource staticResource;
	private int width;
	private int height;

	public ImageResizingRequest() {
		super();
	}

	public ImageResizingRequest(StaticResource staticResource, int width, int height) {
		super();
		this.staticResource = staticResource;
		this.width = width;
		this.height = height;
	}

	public StaticResource getStaticResource() {
		return staticResource;
	}

	public void setStaticResource(StaticResource staticResource) {
		this.staticResource = staticResource;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
