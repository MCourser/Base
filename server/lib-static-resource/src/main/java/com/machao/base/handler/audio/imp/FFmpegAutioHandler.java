package com.machao.base.handler.audio.imp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.machao.base.ffmpeg.FFmpegHandler.HandlerCallback;
import com.machao.base.ffmpeg.imp.M3u8FFmpegHandler;
import com.machao.base.ffmpeg.imp.TsFFmpegHandler;
import com.machao.base.ffmpeg.utils.PlatformUtils.UnsupportedArchException;
import com.machao.base.ffmpeg.utils.PlatformUtils.UnsupportedPlatformException;
import com.machao.base.handler.audio.AudioHandler;

public class FFmpegAutioHandler implements AudioHandler {
	private Logger logger = LoggerFactory.getLogger(FFmpegAutioHandler.class);
	
	private TsFFmpegHandler tsFFmpegHandler = new TsFFmpegHandler();
	private M3u8FFmpegHandler m3u8FFmpegHandler = new M3u8FFmpegHandler();

	@Override
	public void handle(File file) throws IOException, InterruptedException, UnsupportedPlatformException, UnsupportedArchException {
		List<File> tsList = new ArrayList<File>();
		this.tsFFmpegHandler.handle(file, file.getParentFile(), new HandlerCallback() {
			@Override
			public void onStart() {
				logger.info("TsFFmpegHandler ==> start: {}", file);
			}
			
			@Override
			public void onGenerateOutputFile(File destFile) {
				logger.info("TsFFmpegHandler ==> new file: {}", destFile);
				tsList.add(destFile);
			}
			
			@Override
			public void onFinished() {
				logger.info("TsFFmpegHandler ==> finished: {}", file);
			}
			
			@Override
			public void onError(String error) {
				logger.error("TsFFmpegHandler ==> error: {}, {}", file, error);
			}
		});
		
		List<File> m3u8List = new ArrayList<File>();
		for(File destFile : tsList) {
			this.m3u8FFmpegHandler.handle(destFile, destFile.getParentFile(), new HandlerCallback() {
				@Override
				public void onStart() {
					logger.info("M3u8FFmpegHandler ==> start: {}", file);
				}
				
				@Override
				public void onGenerateOutputFile(File destFile) {
					logger.info("M3u8FFmpegHandler ==> new file: {}", destFile);
					m3u8List.add(destFile);
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
}
