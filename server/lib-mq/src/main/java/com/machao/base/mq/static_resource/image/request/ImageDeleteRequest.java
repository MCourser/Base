package com.machao.base.mq.static_resource.image.request;

import java.io.File;
import java.io.Serializable;

public class ImageDeleteRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	private File file;
	
	public ImageDeleteRequest() {
		super();
	}

	public ImageDeleteRequest(File file) {
		super();
		this.file = file;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
