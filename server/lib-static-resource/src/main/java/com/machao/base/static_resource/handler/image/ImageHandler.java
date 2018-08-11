package com.machao.base.static_resource.handler.image;

import java.io.File;

import com.machao.base.static_resource.handler.Handler;

public interface ImageHandler extends Handler{
	File resize(File file, int width, int height, Callback callback);
	
	interface Callback {
		String getDestinationPath(int width, int height);
	}
}
