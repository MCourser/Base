package com.machao.base.mq.static_resource.audio.request;

import java.io.File;
import java.io.Serializable;

public class AudioPlayListRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	private File file;
	
	public AudioPlayListRequest() {
		super();
	}

	public AudioPlayListRequest(File file) {
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
