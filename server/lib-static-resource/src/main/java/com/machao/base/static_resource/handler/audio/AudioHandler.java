package com.machao.base.static_resource.handler.audio;

import java.io.File;
import java.io.IOException;

import com.machao.base.static_resource.ffmpeg.utils.PlatformUtils.UnsupportedArchException;
import com.machao.base.static_resource.ffmpeg.utils.PlatformUtils.UnsupportedPlatformException;

public interface AudioHandler {
	void handle(File file) throws IOException, InterruptedException, UnsupportedPlatformException, UnsupportedArchException;
}
