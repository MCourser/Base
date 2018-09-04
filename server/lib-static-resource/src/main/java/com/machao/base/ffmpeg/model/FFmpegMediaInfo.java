package com.machao.base.ffmpeg.model;

import java.util.List;

public class FFmpegMediaInfo {

	private List<Stream> streams;
	private Format format;

	public void setStreams(List<Stream> streams) {
		this.streams = streams;
	}

	public List<Stream> getStreams() {
		return streams;
	}

	public void setFormat(Format format) {
		this.format = format;
	}

	public Format getFormat() {
		return format;
	}

}