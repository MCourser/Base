package com.machao.base.mq.static_resource.audio.response;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AudioPlayListResponse implements Serializable {
	public static final String M3U8_CONTENT_TYPE = "application/vnd.apple.mpegurl";
	public static final String TS_CONTENT_TYPE = "audio/mp2t";
	
	private static final long serialVersionUID = 1L;
	private File m3u8File;
	private List<File> tsFiles = new ArrayList<File>();
	private String url;

	public AudioPlayListResponse() {
		super();
	}

	public AudioPlayListResponse(File m3u8File, List<File> tsFiles) {
		super();
		this.m3u8File = m3u8File;
		this.tsFiles = tsFiles;
	}

	public File getM3u8File() {
		return m3u8File;
	}

	public void setM3u8File(File m3u8File) {
		this.m3u8File = m3u8File;
	}

	public List<File> getTsFiles() {
		return tsFiles;
	}

	public void setTsFiles(List<File> tsFiles) {
		this.tsFiles = tsFiles;
	}

	public void addTsFile(File tsFile) {
		this.tsFiles.add(tsFile);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "AudioPlayListResponse [m3u8File=" + m3u8File + ", tsFiles=" + tsFiles + ", url=" + url + "]";
	}
	
	

}
