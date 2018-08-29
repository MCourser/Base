package com.machao.base.handler.video;

import java.io.File;
import java.io.IOException;

import com.machao.base.ffmpeg.utils.PlatformUtils.UnsupportedArchException;
import com.machao.base.ffmpeg.utils.PlatformUtils.UnsupportedPlatformException;

public interface VideoHandler {
	void handle(File file) throws IOException, InterruptedException, UnsupportedPlatformException, UnsupportedArchException;
}
