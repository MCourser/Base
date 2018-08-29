package com.machao.base.handler.image;

import java.io.File;

import com.machao.base.handler.Handler;

public interface ImageHandler extends Handler{
	File resize(File file, int width, int height, Callback callback);
	
	interface Callback {
		String getDestinationPath(int width, int height);
	}
}
