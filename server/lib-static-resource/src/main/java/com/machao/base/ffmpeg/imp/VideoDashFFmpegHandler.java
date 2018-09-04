package com.machao.base.ffmpeg.imp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.machao.base.commons.FileUtils;
import com.machao.base.ffmpeg.FFmepgHandlerConfig;
import com.machao.base.ffmpeg.FFmpegHandler;
import com.machao.base.ffmpeg.config.FFmpegConfig;
import com.machao.base.ffmpeg.model.FFmpegMediaInfo;
import com.machao.base.ffmpeg.model.Stream;

/**
 *  ffmpeg -y -threads 2 -i test.mp4 -filter_complex 'split=2[s0][s1];[s0]scale=-2:480p[480p];[s1]scale=-2:360[360p]' -map '[480p]' -c:v libx264 -map '[360p]' -c:v libx264 -map a -c:a aac -min_seg_duration 5000000 -use_template 1 -use_timeline 1 -init_seg_name init-\$RepresentationID\$.m4s -media_seg_name test-\$RepresentationID\$-\$Number\$.m4s -adaptation_sets "id=0,streams=v id=1,streams=a" -f dash index.mpd
 */
public class VideoDashFFmpegHandler extends FFmpegHandler {

	@Override
	protected List<String> obtainCommands(FFmepgHandlerConfig ffmepgHandlerConfig, FFmpegMediaInfo ffmpegMediaInfo, File srcFile, File destFolder, HandlerCallback callback) throws Exception {
		int videoHeight = 0;
		for(Stream stream : ffmpegMediaInfo.getStreams()) {
			if("video".equalsIgnoreCase(stream.getCodec_type())) {
				if(stream.getHeight() > videoHeight) {
					videoHeight = stream.getHeight();
				}
			}
		}
		List<VideoQuality> qualities = VideoQuality.obtainQualitiesShouldGenerate(videoHeight);

		List<String> commands = new ArrayList<String>();
		commands.add(FFmpegConfig.getFFmpeg().getAbsolutePath());
		commands.add("-y");
		commands.add("-threads");
		commands.add(String.valueOf(ffmepgHandlerConfig.getThreads()));
		commands.add("-i");
		commands.add(srcFile.getAbsolutePath());
	
		commands.add("-filter_complex");
		StringBuffer filterComplexValue = new StringBuffer();
		filterComplexValue.append("split=").append(qualities.size());
		for(int i=0; i<qualities.size(); i++) {
			filterComplexValue.append("[m").append(i).append("]");
		}
		for(int i=0; i<qualities.size(); i++) {
			VideoQuality quality = qualities.get(i);
			filterComplexValue.append(";").append("[m").append(i).append("]").append("scale=-2:").append(quality.getHeight()).append("[").append(quality.getHeight()).append("p]");
		}
		commands.add(filterComplexValue.toString());
		
		for(int i=0; i<qualities.size(); i++) {
			VideoQuality quality = qualities.get(i);
			commands.add("-map");
			commands.add(String.format("[%dp]", quality.getHeight()));
			commands.add("-c:v");
			commands.add("libx264");
		}
		commands.add("-map");
		commands.add("a?");
		commands.add("-c:a");
		commands.add("aac");
		
		commands.add("-min_seg_duration");
		commands.add("5000000");
		commands.add("-use_template");
		commands.add("1");
		commands.add("-use_timeline");
		commands.add("1");
		commands.add("-init_seg_name");
		commands.add(String.format("%s-$RepresentationID$%s", FileUtils.obtainFileName(srcFile), Type.m4s.getSuffix()));
		commands.add("-media_seg_name");
		commands.add(String.format("%s-$RepresentationID$-$Number$%s", FileUtils.obtainFileName(srcFile), Type.m4s.getSuffix()));
		commands.add("-adaptation_sets");
		commands.add("id=0,streams=v id=1,streams=a");
		commands.add("-f");
		commands.add("dash");
		
		File destTsFile = new File(destFolder.getAbsolutePath(), FileUtils.obtainFileName(srcFile) + Type.mpd.getSuffix());
		commands.add(destTsFile.getAbsolutePath());
		
		return commands;
	}
}
