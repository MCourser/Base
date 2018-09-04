package com.machao.base.handler.audio.imp;

import java.io.File;

import com.machao.base.ffmpeg.FFmpegHandler.HandlerCallback;
import com.machao.base.ffmpeg.imp.AudioDashFFmpegHandler;
import com.machao.base.handler.audio.AudioHandler;

public class FFmpegAudioStreamHandler implements AudioHandler {
	private AudioDashFFmpegHandler dashFFmpegHandler = new AudioDashFFmpegHandler();

	@Override
	public void handle(File file, HandlerCallback callback) throws Exception {
		this.dashFFmpegHandler.handle(file, callback);
	}
}
