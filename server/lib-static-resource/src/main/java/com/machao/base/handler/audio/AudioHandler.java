package com.machao.base.handler.audio;

import java.io.File;

import com.machao.base.ffmpeg.FFmpegHandler.HandlerCallback;

public interface AudioHandler {
	void handle(File file, HandlerCallback callback) throws Exception;
}
