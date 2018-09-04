package com.machao.base.handler.audio.imp;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.machao.base.ffmpeg.FFmpegHandler.HandlerCallback;
import com.machao.base.ffmpeg.imp.AudioM3u8FFmpegHandler;
import com.machao.base.handler.audio.AudioHandler;

public class FFmpegAudioStreamHandler implements AudioHandler {
	private static final Logger logger = LoggerFactory.getLogger(FFmpegAudioStreamHandler.class);
	
	private AudioM3u8FFmpegHandler m3u8FFmpegHandler = new AudioM3u8FFmpegHandler();

	@Override
	public void handle(File file) throws Exception {
		this.m3u8FFmpegHandler.handle(file, new HandlerCallback() {
			@Override
			public void onStart() {
				logger.info("M3u8FFmpegHandler ==> start: {}", file);
			}
			
			@Override
			public void onGenerateOutputFile(File destFile) {
				logger.info("M3u8FFmpegHandler ==> new file: {}", destFile);
			}
			
			@Override
			public void onFinished() {
				logger.info("M3u8FFmpegHandler ==> finished: {}", file);
			}
			
			@Override
			public void onError(String error) {
				logger.error("M3u8FFmpegHandler ==> error: {}, {}", file, error);
			}
		});
	}
}
