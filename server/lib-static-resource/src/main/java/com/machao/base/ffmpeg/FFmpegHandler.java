package com.machao.base.ffmpeg;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.machao.base.ffmpeg.utils.PlatformUtils.UnsupportedArchException;
import com.machao.base.ffmpeg.utils.PlatformUtils.UnsupportedPlatformException;

public abstract class FFmpegHandler {
	private static final Logger logger = LoggerFactory.getLogger(FFmpegHandler.class);
	
	public enum Type {
		unknow("unknow"),
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
		
		public static Type valueOf(File file) {
			String fileName = file.getName();
			for(Type type : Type.values()) {
				if(fileName.endsWith(type.getName())) {
					return type;
				}
			}
			return Type.unknow;
		}
	}

	/**
	 * 
	 * @param srcFile src must is file
	 * @param destFolder dest must is folder
	 * @return true is success
	 * @throws IOException
	 * @throws InterruptedException 
	 * @throws UnsupportedArchException 
	 * @throws UnsupportedPlatformException 
	 */
	public final boolean handle(File srcFile, File destFolder, HandlerCallback callback) throws IOException, InterruptedException, UnsupportedPlatformException, UnsupportedArchException {
		if(!srcFile.isFile()) {
			throw new IllegalArgumentException("srcFile must is a video file.");
		}
		
		if (!destFolder.exists()) {
			destFolder.createNewFile();
			
			if(!destFolder.isDirectory()) {
				FileUtils.deleteQuietly(destFolder);
				throw new IllegalArgumentException("destFolder must is a folder.");
			}
		} else {
			if(!destFolder.isDirectory()) {
				throw new IllegalArgumentException("destFolder must is a folder.");
			}
		}

		callback.onStart();
		
		Process videoProcess = new ProcessBuilder(obtainCommands(srcFile, destFolder, callback)).redirectErrorStream(true).start();
		new PrintStream(videoProcess.getErrorStream(), callback).start();
		new PrintStream(videoProcess.getInputStream(), callback).start();
		int exitcode = videoProcess.waitFor();
		
		callback.onFinished();

		if (exitcode == 1) {
			return false;
		}
		return true;
	}
	
	protected abstract List<String> obtainCommands(File srcFile, File destFolder, HandlerCallback callback)  throws UnsupportedPlatformException, UnsupportedArchException, IOException;
	
	private class PrintStream extends Thread {
		private InputStream inputStream = null;
		private StringBuffer lineBuffer = new StringBuffer();
		private HandlerCallback callback = null;

		public PrintStream(InputStream inputStream, HandlerCallback callback) {
			this.inputStream = inputStream;
			this.callback = callback;
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
								if(callback != null) {
									this.callback.onError(lineBuffer.toString());
								}
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
		public ErrorPrintStream(InputStream inputStream, HandlerCallback callback) {
			super(inputStream, callback);
		}
	}
	private class InputPrintStream extends PrintStream{
		public InputPrintStream(InputStream inputStream, HandlerCallback callback) {
			super(inputStream, callback);
		}
	}

	public interface HandlerCallback {
		public void onStart();
		public void onError(String error);
		public void onGenerateOutputFile(File destFile);
		public void onFinished();
	}
	
}
