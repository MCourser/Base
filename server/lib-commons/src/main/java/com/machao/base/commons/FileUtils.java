package com.machao.base.commons;

import java.io.File;

public class FileUtils {
	public static String obtainFileName(File file) {
		String fileName = file.getName();
		int dotIndex = fileName.indexOf('.');
		if(dotIndex < 0) return file.getName();
		return fileName.substring(0, dotIndex);
	}
}
