package com.machao.base.static_resource.handler;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class LockableHandler implements Handler {
	private Logger logger = LoggerFactory.getLogger(LockableHandler.class);
	
	public static final String LOCK_FILE_NAME = ".lock";
	
	public void lock(File targetFile) {
		logger.error("lock file: {}", targetFile.getAbsolutePath());
		try {
			File lockFile = obtainLockFile(targetFile);
			FileUtils.writeStringToFile(lockFile, lockFile.getAbsolutePath());
		} catch (IOException e) {
			logger.error("error to lock file: {}", targetFile.getAbsolutePath());
		}
	}
	
	public void unlock(File targetFile) {
		logger.error("unlock file: {}", targetFile.getAbsolutePath());
		try {
			File lockFile = obtainLockFile(targetFile);
			FileUtils.deleteQuietly(lockFile);
		} catch (Exception e) {
			logger.error("error to lock file: {}", targetFile.getAbsolutePath());
		}
	}
	
	public boolean isLocked(File targetFile) {
		return obtainLockFile(targetFile).exists();
	}
	
	protected File obtainLockFile(File targetFile) {
		return new File(targetFile.getParent(), LOCK_FILE_NAME);
	}
	
}
