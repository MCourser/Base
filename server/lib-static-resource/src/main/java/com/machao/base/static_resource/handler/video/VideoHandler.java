package com.machao.base.static_resource.handler.video;

import java.io.File;
import java.io.IOException;

import com.machao.base.static_resource.ffmpeg.utils.PlatformUtils.UnsupportedArchException;
import com.machao.base.static_resource.ffmpeg.utils.PlatformUtils.UnsupportedPlatformException;

public interface VideoHandler {
	void handle(File file) throws IOException, InterruptedException, UnsupportedPlatformException, UnsupportedArchException;
}
