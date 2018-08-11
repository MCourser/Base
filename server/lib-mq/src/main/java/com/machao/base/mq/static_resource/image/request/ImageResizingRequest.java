package com.machao.base.mq.static_resource.image.request;

import java.io.File;
import java.io.Serializable;

public class ImageResizingRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	private File file;
	private int width;
	private int height;
	private String contentType;
	
	public ImageResizingRequest() {
		super();
	}
	
	public ImageResizingRequest(File file, int width, int height, String contentType) {
		super();
		this.file = file;
		this.width = width;
		this.height = height;
		this.contentType = contentType;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
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

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}
