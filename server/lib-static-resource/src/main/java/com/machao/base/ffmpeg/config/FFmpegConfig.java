package com.machao.base.ffmpeg.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.machao.base.ffmpeg.utils.PlatformUtils;
import com.machao.base.ffmpeg.utils.PlatformUtils.Arch;
import com.machao.base.ffmpeg.utils.PlatformUtils.Platform;
import com.machao.base.ffmpeg.utils.PlatformUtils.UnsupportedArchException;
import com.machao.base.ffmpeg.utils.PlatformUtils.UnsupportedPlatformException;

public class FFmpegConfig {
	private static File libPath() throws IOException {
		File userDir = new File(System.getProperty("user.dir"));
		return new File(userDir.getParent(), "ffmpeg");
	}
	
	public static File getFFmpegHome() throws UnsupportedPlatformException, UnsupportedArchException, IOException {
		Platform platform = PlatformUtils.getPlatform();
		Arch arch = PlatformUtils.getArch();
		if(Platform.macos.equals(platform)) {
			return new File(libPath(), Platform.macos + "-" + arch);
		} else if(Platform.linux.equals(platform)) {
			return new File(libPath(), Platform.linux + "-" + arch);
		} else if(Platform.windows.equals(platform)) {
			return new File(libPath(), Platform.windows + "-" + arch);
		}
		
		throw new FileNotFoundException();
	}
	
	public static File getFFmpegPath() throws UnsupportedPlatformException, UnsupportedArchException, IOException {
		Platform platform = PlatformUtils.getPlatform();
		File home = getFFmpegHome();
		if(Platform.macos.equals(platform)) {
			return new File(home.getAbsolutePath(), "bin");
		} else if(Platform.linux.equals(platform)) {
			return home;
		} else if(Platform.windows.equals(platform)) {
			return new File(home.getAbsolutePath(), "bin");
		}
		
		throw new FileNotFoundException();
	}
	
	public static File getFFmpeg() throws UnsupportedPlatformException, UnsupportedArchException, IOException {
		Platform platform = PlatformUtils.getPlatform();
		File path = getFFmpegPath();
		if(Platform.macos.equals(platform)) {
			return new File(path.getAbsolutePath(), "ffmpeg");
		} else if(Platform.linux.equals(platform)) {
			return new File(path.getAbsolutePath(), "ffmpeg");
		} else if(Platform.windows.equals(platform)) {
			return new File(path.getAbsolutePath(), "ffmpeg.exe");
		}
		
		throw new FileNotFoundException();
	}
	
	public static File getFFprobe() throws UnsupportedPlatformException, UnsupportedArchException, IOException {
		Platform platform = PlatformUtils.getPlatform();
		File path = getFFmpegPath();
		if(Platform.macos.equals(platform)) {
			return new File(path.getAbsolutePath(), "ffprobe");
		} else if(Platform.linux.equals(platform)) {
			return new File(path.getAbsolutePath(), "ffprobe");
		} else if(Platform.windows.equals(platform)) {
			return new File(path.getAbsolutePath(), "ffprobe.exe");
		}
		
		throw new FileNotFoundException();
	}
}
