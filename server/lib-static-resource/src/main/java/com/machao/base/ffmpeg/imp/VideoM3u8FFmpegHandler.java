package com.machao.base.ffmpeg.imp;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.machao.base.commons.FileUtils;
import com.machao.base.ffmpeg.FFmepgHandlerConfig;
import com.machao.base.ffmpeg.FFmpegHandler;
import com.machao.base.ffmpeg.config.FFmpegConfig;
import com.machao.base.ffmpeg.model.FFmpegMediaInfo;
import com.machao.base.ffmpeg.model.Stream;

public class VideoM3u8FFmpegHandler extends FFmpegHandler {
	private static final Logger logger = LoggerFactory.getLogger(FFmpegHandler.class);
	 
	/**
	 * Quality				Resolution	bitrate - low motion	bitrate - high motion	audio bitrate
	 * 240p					426x240		400k					600k					64k
	 * 360p					640x360		700k					900k					96k
	 * 480p					854x480		1250k					1600k					128k
	 * HD 720p				1280x720	2500k					3200k					128k
	 * Full HD 1080p		1920x1080	4500k					5300k					192k
	 * 2k					1152x2048    
	 * 4k					3840x2160	14000k					18200k					192k
	 *
	 */
	public enum Quality {
		P_240(320, 240, 400000, 600000), 
		P_360(480, 360, 700000, 900000), 
		P_480(640, 480, 1260000, 1600000), 
		P_720(1280, 720, 2500000, 3200000), 
		P_1080(1920, 1080, 4500000, 5300000), 
		P_2K(1152, 2048, 7000000, 9600000), 
		P_4K(2304, 4096, 14000000, 18200000);
		
		private int width;
		private int height;
		private int lowMotionBitrate;
		private int highMotionBitrate;
		
		private Quality(int width, int height, int lowMotionBitrate, int highMotionBitrate) {
			this.width = width;
			this.height = height;
			this.lowMotionBitrate = lowMotionBitrate;
			this.highMotionBitrate = highMotionBitrate;
		}
		public int getWidth() {
			return width;
		}
		public int getHeight() {
			return height;
		}
		public int getLowMotionBitrate() {
			return lowMotionBitrate;
		}
		public int getHighMotionBitrate() {
			return highMotionBitrate;
		}
		public int getMiddleMotionBitrate() {
			return (lowMotionBitrate+highMotionBitrate)/2;
		}
		
		public static List<Quality> obtainQualitiesShouldGenerate(int height) {
			List<Quality> retList = new ArrayList<>();
			for(Quality quality : Quality.values()) {
				if(quality.height <= height) {
					retList.add(quality);
				}
			}
			
			Collections.reverse(retList);
			
			return retList;
		}
	}
	
	//	ffmpeg -i output.ts -c copy -map 0 -f segment -segment_list playlist.m3u8 -segment_time 10 output%03d.ts
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
		
		List<String> commands = new ArrayList<String>();
//		ffmpeg -i 2015-09-17.mp4 -c:v libx264 -c:a aac -strict -2 -vf scale=-2:680 -f hls 680/target.m3u8
		commands.add(FFmpegConfig.getFFmpeg().getAbsolutePath());
		commands.add("-y");
		commands.add("-threads");
		commands.add(String.valueOf(ffmepgHandlerConfig.getThreads()));
		commands.add("-i");
		commands.add(srcFile.getAbsolutePath());
		
		StringBuffer masterM3u8StringBuffer = new StringBuffer();
		masterM3u8StringBuffer.append("#EXTM3U").append('\n');
		File masterM3u8File = new File(destFolder.getAbsolutePath(), FileUtils.obtainFileName(srcFile) + Type.m3u8.getSuffix());
		Quality.obtainQualitiesShouldGenerate(videoHeight).forEach(quality->{
			commands.add("-c:v");
			commands.add("libx264");
			commands.add("-c:a");
			commands.add("aac");
			commands.add("-b");
			commands.add(String.valueOf(quality.getMiddleMotionBitrate()));
			commands.add("-minrate");
			commands.add(String.valueOf(quality.getLowMotionBitrate()));
			commands.add("-maxrate");
			commands.add(String.valueOf(quality.getHighMotionBitrate()));
			commands.add("-strict");
			commands.add("2");
			commands.add("-vf");
			commands.add(String.format("scale=-2:%d", quality.getHeight()));
			commands.add("-f");
			commands.add("segment");
			commands.add("-segment_time");
			commands.add(String.valueOf(ffmepgHandlerConfig.getSegmentTime()));
			commands.add("-segment_format");
			commands.add("mpegts");
			
			File newQualityDestFolder = new File(destFolder.getAbsolutePath(), String.valueOf(quality.getHeight()));
			if(!newQualityDestFolder.exists()) newQualityDestFolder.mkdir();
			File slaveM3u8File = new File(newQualityDestFolder.getAbsolutePath(), FileUtils.obtainFileName(srcFile) + Type.m3u8.getSuffix());
			callback.onGenerateOutputFile(slaveM3u8File);
			commands.add("-segment_list");
			commands.add(slaveM3u8File.getAbsolutePath());
			
			File destTsFile = new File(newQualityDestFolder.getAbsolutePath(), FileUtils.obtainFileName(srcFile) + "-%03d" + Type.ts.getSuffix());
			commands.add(destTsFile.getAbsolutePath());
			
			masterM3u8StringBuffer.append(String.format("#EXT-X-STREAM-INF:PROGRAM-ID=1,BANDWIDTH=%d,RESOLUTION=%dx%d", quality.getMiddleMotionBitrate(), quality.getWidth(), quality.getHeight())).append('\n');
			masterM3u8StringBuffer.append(String.format("%d" + File.separator + "%s", quality.getHeight(), FileUtils.obtainFileName(srcFile) + Type.m3u8.getSuffix())).append('\n');
		});
		
		logger.debug("generate master m3u8 file: {}", masterM3u8File);
		org.apache.commons.io.FileUtils.writeStringToFile(masterM3u8File, masterM3u8StringBuffer.toString());
		
		return commands;
	}
}
