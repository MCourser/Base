package com.machao.base.ffmpeg.imp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileExistsException;

import com.machao.base.ffmpeg.FFmpegHandler;
import com.machao.base.ffmpeg.config.FFmpegConfig;
import com.machao.base.ffmpeg.utils.PlatformUtils.UnsupportedArchException;
import com.machao.base.ffmpeg.utils.PlatformUtils.UnsupportedPlatformException;

public class TsFFmpegHandler extends FFmpegHandler {
	
	//	ffmpeg -i out.mp4 -c copy -bsf h264_mp4toannexb output.ts
	@Override
	public List<String> obtainCommands(File srcFile, File destFolder, HandlerCallback callback) throws UnsupportedPlatformException, UnsupportedArchException, IOException {
		List<String> commands = new ArrayList<String>();
		
		commands.add(FFmpegConfig.getFFmpeg().getAbsolutePath());
		commands.add("-i");
		commands.add(srcFile.getAbsolutePath());
		commands.add("-c");
		commands.add("copy");
		if(!Type.mp3.equals(Type.valueOf(srcFile))) {
			commands.add("-bsf");
			commands.add("h264_mp4toannexb");
		}
		
		File destFile = new File(destFolder.getAbsolutePath(), obtainFileName(srcFile) + Type.ts.getSuffix());
		if(destFile.exists()) throw new FileExistsException(destFile);
		
		callback.onGenerateOutputFile(destFile);
		commands.add(destFile.getAbsolutePath());
		
		System.out.println(commands);
		
		return commands;
	}

}
