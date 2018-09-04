package com.machao.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.machao.base.handler.audio.imp.FFmpegAudioStreamHandler;
import com.machao.base.handler.image.imp.OpencvImageHandler;
import com.machao.base.handler.video.imp.FFmpegVideoStreamHandler;

@Configuration
public class HandlerConfig {

	@Bean
	public OpencvImageHandler opencvImageHandler() {
		return new OpencvImageHandler();
	}
	
	@Bean
	public FFmpegAudioStreamHandler ffmpegAutioHandler() {
		return new FFmpegAudioStreamHandler();
	}
	
	@Bean
	public FFmpegVideoStreamHandler ffmpegVideoHandler() {
		return new FFmpegVideoStreamHandler();
	}
}
