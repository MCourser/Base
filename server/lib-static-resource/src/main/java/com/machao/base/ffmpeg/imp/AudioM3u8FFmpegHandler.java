package com.machao.base.ffmpeg.imp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.machao.base.commons.FileUtils;
import com.machao.base.ffmpeg.FFmepgHandlerConfig;
import com.machao.base.ffmpeg.FFmpegHandler;
import com.machao.base.ffmpeg.config.FFmpegConfig;
import com.machao.base.ffmpeg.model.FFmpegMediaInfo;

public class AudioM3u8FFmpegHandler extends FFmpegHandler {

//	ffmpeg -i output.ts -c copy -map 0 -f segment -segment_list playlist.m3u8 -segment_time 10 output%03d.ts
	@Override
	protected List<String> obtainCommands(FFmepgHandlerConfig ffmepgHandlerConfig, FFmpegMediaInfo ffmpegMediaInfo, File srcFile, File destFolder, HandlerCallback callback) throws Exception {
		List<String> commands = new ArrayList<String>();
//		ffmpeg -i input.wav -c:a libmp3lame -b:a 128k -map 0:0 -f segment -segment_time 10 -segment_list outputlist.m3u8 -segment_format mpegts 'output%03d.mp3'
		commands.add(FFmpegConfig.getFFmpeg().getAbsolutePath());
		commands.add("-y");
		commands.add("-threads");
		commands.add(String.valueOf(ffmepgHandlerConfig.getThreads()));
		commands.add("-i");
		commands.add(srcFile.getAbsolutePath());
		commands.add("-c:a");
		commands.add("aac");
		commands.add("-f");
		commands.add("segment");
		commands.add("-segment_time");
		commands.add("10");
		commands.add("-segment_format");
		commands.add("mpegts");
		
		File destM3u8File = new File(destFolder.getAbsolutePath(), FileUtils.obtainFileName(srcFile) + Type.m3u8.getSuffix());
		callback.onGenerateOutputFile(destM3u8File);
		commands.add("-segment_list");
		commands.add(destM3u8File.getAbsolutePath());
		
		File destTsFile = new File(destFolder.getAbsolutePath(), FileUtils.obtainFileName(srcFile) + "-%03d" + Type.ts.getSuffix());
		commands.add(destTsFile.getAbsolutePath());
		
		return commands;
	}
}
