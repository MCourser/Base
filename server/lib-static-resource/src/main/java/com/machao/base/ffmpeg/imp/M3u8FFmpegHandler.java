package com.machao.base.ffmpeg.imp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.machao.base.commons.FileUtils;
import com.machao.base.ffmpeg.FFmpegHandler;
import com.machao.base.ffmpeg.config.FFmpegConfig;
import com.machao.base.ffmpeg.utils.PlatformUtils.UnsupportedArchException;
import com.machao.base.ffmpeg.utils.PlatformUtils.UnsupportedPlatformException;

public class M3u8FFmpegHandler extends FFmpegHandler {

	//	ffmpeg -i output.ts -c copy -map 0 -f segment -segment_list playlist.m3u8 -segment_time 10 output%03d.ts
	@Override
	protected List<String> obtainCommands(File srcFile, File destFolder, HandlerCallback callback) throws UnsupportedPlatformException, UnsupportedArchException, IOException {
		List<String> commands = new ArrayList<String>();
		
		commands.add(FFmpegConfig.getFFmpeg().getAbsolutePath());
		commands.add("-y");
		commands.add("-i");
		commands.add(srcFile.getAbsolutePath());
		commands.add("-c");
		commands.add("copy");
		commands.add("-map");
		commands.add("0");
		commands.add("-f");
		commands.add("segment");
//		commands.add("-segment_time");
//		commands.add("5");
		
		File destM3u8File = new File(destFolder.getAbsolutePath(), FileUtils.obtainFileName(srcFile) + Type.m3u8.getSuffix());
		callback.onGenerateOutputFile(destM3u8File);
		commands.add("-segment_list");
		commands.add(destM3u8File.getAbsolutePath());
		
		File destTsFile = new File(destFolder.getAbsolutePath(), FileUtils.obtainFileName(srcFile) + "-%03d" + Type.ts.getSuffix());
		commands.add(destTsFile.getAbsolutePath());
		
		return commands;
	}

}
