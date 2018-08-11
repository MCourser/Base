package com.machao.base.mq.static_resource.audio.request;

import java.io.File;
import java.io.Serializable;

public class AudioConvertRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	private File file;
	
	public AudioConvertRequest() {
		super();
	}

	public AudioConvertRequest(File file) {
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
