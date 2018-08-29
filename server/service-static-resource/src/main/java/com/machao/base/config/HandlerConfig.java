package com.machao.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.machao.base.handler.audio.imp.FFmpegAutioHandler;
import com.machao.base.handler.image.imp.OpencvImageHandler;
import com.machao.base.handler.video.imp.FFmpegVideoHandler;

@Configuration
public class HandlerConfig {

	@Bean
	public OpencvImageHandler opencvImageHandler() {
		return new OpencvImageHandler();
	}
	
	@Bean
	public FFmpegAutioHandler ffmpegAutioHandler() {
		return new FFmpegAutioHandler();
	}
	
	@Bean
	public FFmpegVideoHandler ffmpegVideoHandler() {
		return new FFmpegVideoHandler();
	}
}
