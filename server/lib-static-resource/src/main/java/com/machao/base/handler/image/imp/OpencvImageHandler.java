package com.machao.base.handler.image.imp;

import java.io.File;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.opencv_java;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.machao.base.handler.image.ImageHandler;

public class OpencvImageHandler implements ImageHandler{
	
	static {
		Loader.load(opencv_java.class);
	}
	
	@Override
	public File resize(File file, int width, int height, Callback callback) {
		try {
			Mat src = Imgcodecs.imread(file.getAbsolutePath());
			Mat dst = new Mat();
			
			int newWidth = src.width();
			int newHeight = src.height();
			if(width != 0 && height == 0) {
				newWidth = width > src.width() ? src.width() : width;
				newHeight = width > src.width() ? src.height() : (int)(((double)width)/src.width()*src.height());
			} else if(width == 0 && height != 0) {
				newWidth = height > src.height() ? src.width() : (int)(((double)height)/src.height()*src.width());
				newHeight = height > src.height() ? src.height() : height;
			} else if(width != 0 && height != 0) {
				newWidth = width > src.width() ? src.width() : width;
				newHeight = height > src.height() ? src.height() : height;
			} 
			
			Size size = new Size(newWidth, newHeight);
			Imgproc.resize(src, dst, size , 0, 0, Imgproc.INTER_AREA);
			
			File newFile = new File(callback.getDestinationPath(newWidth, newHeight));
			if(!newFile.exists()) {
				Imgcodecs.imwrite(newFile.getAbsolutePath(), dst);
			}
			
			return newFile;
		} catch (Exception e) {
			return file;
		}
	}
	
}
