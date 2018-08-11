package com.machao.base.mq.static_resource.video.request;

import java.io.File;
import java.io.Serializable;

public class VideoConvertRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	private File file;
	
	public VideoConvertRequest() {
		super();
	}

	public VideoConvertRequest(File file) {
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
