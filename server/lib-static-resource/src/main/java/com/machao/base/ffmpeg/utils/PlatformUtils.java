package com.machao.base.ffmpeg.utils;

public class PlatformUtils {
	private static final String OS_NAME_MAC = "mac os";
	private static final String OS_NAME_LINUX = "linux";
	private static final String OS_NAME_WINDOWS = "windows";

	public enum Platform {
		macos, linux, windows
	}
	
	public enum Arch {
		x86, x86_64
	}
	
	public static class UnsupportedPlatformException extends Exception {
		private static final long serialVersionUID = 1L;
		
		public UnsupportedPlatformException(String platform) {
			super("unsupport platform: " + platform);
		}
	}
	
	public static class UnsupportedArchException extends Exception {
		private static final long serialVersionUID = 1L;

		public UnsupportedArchException(String arch) {
			super("unsupport arch: " + arch);
		}
	}
	
	public static Arch getArch() throws UnsupportedArchException {
		String osArch = System.getProperty("os.arch");
		if(osArch.equals(Arch.x86.toString())) {
			return Arch.x86;
		} else if(osArch.equals(Arch.x86_64.toString())) {
			return Arch.x86_64;
		}
		
		throw new UnsupportedArchException(osArch);
	}
	
	public static Platform getPlatform() throws UnsupportedPlatformException {
		String osName = System.getProperty("os.name").toLowerCase();
		if(osName.startsWith(OS_NAME_MAC)) {
			return Platform.macos;
		} else if(osName.startsWith(OS_NAME_LINUX)) {
			return Platform.macos;
		} else if (osName.startsWith(OS_NAME_WINDOWS)) {
			return Platform.windows;
		}
		
		throw new UnsupportedPlatformException(osName);
	}
	
}
