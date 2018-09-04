package com.machao.base.ffmpeg;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.machao.base.ffmpeg.config.FFmpegConfig;
import com.machao.base.ffmpeg.model.FFmpegMediaInfo;

public abstract class FFmpegHandler {
	private static final Logger logger = LoggerFactory.getLogger(FFmpegHandler.class);
	
	private FFmepgHandlerConfig ffmepgHandlerConfig = new FFmepgHandlerConfig();
	
	public enum Type {
		avi("avi"), 
		mpg("mpg"),  
		wmv("wmv"),  
		tgp("tgp"),  
		mov("mov"),  
		mp4("mp4"),  
		mkv("mkv"),  
		asf("asf"),  
		flv("flv"),  
		rm("rm"),  
		rmvb("rmvb"),  
		m3u8("m3u8"),  
		ts("ts"),
		mpd("mpd"),
		m4s("m4s"),  
		mp3("mp3");
		
		private String name;

		private Type(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
		
		public String getSuffix() {
			return "." + name;
		}
	}
	
	public FFmpegHandler() {
		super();
	}

	public FFmpegHandler(FFmepgHandlerConfig ffmepgHandlerConfig) {
		super();
		this.ffmepgHandlerConfig = ffmepgHandlerConfig;
	}
	
	private FFmpegMediaInfo preHandle(File srcFile) throws Exception {
		List<String> command = obtainFFprobeCommands(srcFile);
		logger.debug("ffmpeg command: {}", command);
		Process ffprobeProcess = new ProcessBuilder(command).redirectErrorStream(true).start();

		BufferedReader reader = new BufferedReader(new InputStreamReader(ffprobeProcess.getInputStream()));
		StringBuffer lines = new StringBuffer();
		String line = null;
		while((line=reader.readLine()) != null) {
			lines.append(line).append('\n');
		}
		
		ffprobeProcess.waitFor();
		
		logger.debug("ffprobe {}", lines.toString());
		
		return JSONObject.parseObject(lines.toString(), FFmpegMediaInfo.class); 
	}

	/**
	 * 
	 * @param srcFile src must is file
	 * @return true is success
	 * @throws Exception 
	 */
	public final void handle(File srcFile, HandlerCallback callback) throws Exception {
		if(!srcFile.isFile()) {
			throw new IllegalArgumentException("srcFile must is a video file.");
		}
		
		FFmpegMediaInfo ffmpegMediaInfo = this.preHandle(srcFile);
		
		if(callback!=null) callback.onStart();
		
		List<String> command = obtainCommands(ffmepgHandlerConfig, ffmpegMediaInfo, srcFile, srcFile.getParentFile(), callback);
		logger.debug("ffmpeg command: {}", command);
		Process videoProcess = new ProcessBuilder(command).start();
		new ErrorPrintStream(videoProcess.getErrorStream()).start();
		new InputPrintStream(videoProcess.getInputStream()).start();
		int exitCode = videoProcess.waitFor();
		if(callback!=null) callback.onResult(exitCode == 0);
		
		if(callback!=null) callback.onFinished();
	}
	
	private List<String> obtainFFprobeCommands(File srcFile) throws Exception {
		List<String> commands = new ArrayList<String>();
		commands.add(FFmpegConfig.getFFprobe().getAbsolutePath());
		commands.add("-v");
		commands.add("quiet");
		commands.add("-print_format");
		commands.add("json");
		commands.add("-show_format");
		commands.add("-show_streams");
		commands.add(srcFile.getAbsolutePath());
		return commands;
	}
	
	protected abstract List<String> obtainCommands(FFmepgHandlerConfig ffmepgHandlerConfig, FFmpegMediaInfo ffmpegMediaInfo, File srcFile, File destFolder, HandlerCallback callback)  throws Exception;
	
	private class PrintStream extends Thread {
		private InputStream inputStream = null;
		private StringBuffer lineBuffer = new StringBuffer();
		
		public PrintStream(InputStream inputStream) {
			this.inputStream = inputStream;
		}

		public void run() {
			try {
				while (this != null) {
					int _ch = inputStream.read();
					if (_ch == -1) {
						break;
					} else {
						char ch = (char) _ch;
						if(ch != '\n') {
							lineBuffer.append(ch);
						} else {
							if(this instanceof ErrorPrintStream) {
								logger.error("ffmpeg: {}", lineBuffer.toString());
							} else if(this instanceof InputPrintStream) {
								logger.debug("ffmpeg: {}", lineBuffer.toString());
							}
							lineBuffer.setLength(0);
						}
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private class ErrorPrintStream extends PrintStream{
		public ErrorPrintStream(InputStream inputStream) {
			super(inputStream);
		}
	}
	private class InputPrintStream extends PrintStream{
		public InputPrintStream(InputStream inputStream) {
			super(inputStream);
		}
	}

	public interface HandlerCallback {
		public void onStart();
		public void onGenerateOutputFile(File destFile);
		public void onResult(boolean isSuccess);
		public void onFinished();
	}
	
}
