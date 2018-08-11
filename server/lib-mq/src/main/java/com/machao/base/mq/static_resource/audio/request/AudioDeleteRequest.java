package com.machao.base.mq.static_resource.audio.request;

import java.io.File;
import java.io.Serializable;

public class AudioDeleteRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	private File file;
	
	public AudioDeleteRequest() {
		super();
	}

	public AudioDeleteRequest(File file) {
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
