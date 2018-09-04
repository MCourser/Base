package com.machao.base.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.machao.base.ffmpeg.FFmpegHandler.Type;
import com.machao.base.model.persit.StaticResource;

@Component
public class StaticResourcePathUtils {
	
	@Autowired
    private ServerProperties serverProperties;

	private String obtainContextPath() {
		StringBuilder sb = new StringBuilder();
		sb.append(serverProperties.getSsl().isEnabled()?"https://":"http://").append(serverProperties.getAddress().getHostAddress()).append(":").append(serverProperties.getPort()).append(serverProperties.getServlet().getContextPath());
		return sb.toString();
	}
	
	public String imageUrl(StaticResource staticResource, int width, int height) {
		return obtainContextPath() + "/image/" + staticResource.getId() + "?w=" + width + "&h=" + height;
	}
	
	public String audioUrl(StaticResource staticResource) {
		return obtainContextPath() + "/audio/" + staticResource.getId() + "/";
	}
	
	public String videoUrl(StaticResource staticResource) {
		return obtainContextPath() + "/video/" + staticResource.getId() + "/";
	}

	public String obtainAudioM3u8FileContent(StaticResource staticResource) throws IOException {
		if(!StaticResource.Type.audio.equals(staticResource.getType())) return "";
		
		StringBuffer newLines = new StringBuffer();
		File file = new File(staticResource.getPath());
		File m3u8File = new File(file.getParent(), com.machao.base.commons.FileUtils.obtainFileName(file) + Type.m3u8.getSuffix());
		List<String> lines = FileUtils.readLines(m3u8File);
		lines.stream().filter(line->!StringUtils.isEmpty(line)).forEach(line->{
			if(!line.startsWith("#")) {
				String tsUrl = obtainContextPath() + "/audio/" + staticResource.getId() + "/" + line;
				newLines.append(tsUrl).append('\n');
			} else {
				newLines.append(line).append('\n');
			}
		});
		return newLines.toString();
	}
	
	public String obtainVideoM3u8FileContent(StaticResource staticResource) throws IOException {
		if(!StaticResource.Type.video.equals(staticResource.getType())) return "";
		
		StringBuffer newLines = new StringBuffer();
		File file = new File(staticResource.getPath());
		File m3u8File = new File(file.getParent(), com.machao.base.commons.FileUtils.obtainFileName(file) + Type.m3u8.getSuffix());
		List<String> lines = FileUtils.readLines(m3u8File);
		lines.stream().filter(line->!StringUtils.isEmpty(line)).forEach(line->{
			if(!line.startsWith("#")) {
				String tsUrl = obtainContextPath() + "/video/" + staticResource.getId() + "/" + line;
				newLines.append(tsUrl).append('\n');
			} else {
				newLines.append(line).append('\n');
			}
		});
		return newLines.toString();
	}
}
