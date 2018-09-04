package com.machao.base.ffmpeg;

public class FFmepgHandlerConfig {
	private int threads = 2;
	private int segmentTime = 10;

	public int getThreads() {
		return threads;
	}

	public void setThreads(int threads) {
		this.threads = threads;
	}

	public int getSegmentTime() {
		return segmentTime;
	}

	public void setSegmentTime(int segmentTime) {
		this.segmentTime = segmentTime;
	}

}
