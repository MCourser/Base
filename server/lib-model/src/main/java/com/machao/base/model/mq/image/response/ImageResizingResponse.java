package com.machao.base.model.mq.image.response;

import java.io.File;
import java.io.Serializable;

public class ImageResizingResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private File file;
	private String url;
	private String contentType;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

}