package com.machao.base.ffmpeg.imp;

import java.util.ArrayList;
import java.util.List;

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
public enum VideoQuality {
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
	
	private VideoQuality(int width, int height, int lowMotionBitrate, int highMotionBitrate) {
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
	
	public static List<VideoQuality> obtainQualitiesShouldGenerate(int height) {
		List<VideoQuality> retList = new ArrayList<>();
		for(VideoQuality quality : VideoQuality.values()) {
			if(quality.height <= height) {
				retList.add(quality);
			}
		}
		
		if(retList.isEmpty()) {
			retList.add(VideoQuality.P_240);
		}
		
		return retList;
	}
}