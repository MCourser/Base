package com.machao.base.handler.video;

import java.io.File;

import com.machao.base.ffmpeg.FFmpegHandler.HandlerCallback;

public interface VideoHandler {
	void handle(File file, HandlerCallback callback) throws Exception;
}
