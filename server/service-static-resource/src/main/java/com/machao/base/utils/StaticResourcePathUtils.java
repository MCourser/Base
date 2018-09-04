package com.machao.base.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Component;

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
	
	public String imageUrl(StaticResource staticResource) {
		return obtainContextPath() + "/image/" + staticResource.getId();
	}
	
	public String imageUrl(StaticResource staticResource, int width, int height) {
		if(width <= 0 || height <= 0) return obtainContextPath() + "/image/" + staticResource.getId();
		return obtainContextPath() + "/image/" + staticResource.getId() + "?w=" + width + "&h=" + height;
	}
	
	public String audioUrl(StaticResource staticResource) {
		return obtainContextPath() + "/audio/" + staticResource.getId() + "/";
	}
	
	public String videoUrl(StaticResource staticResource) {
		return obtainContextPath() + "/video/" + staticResource.getId() + "/";
	}

	public String obtainIndexFileContent(StaticResource staticResource) throws IOException {
		if(!StaticResource.Type.audio.equals(staticResource.getType()) && !StaticResource.Type.video.equals(staticResource.getType())) return "";
		
		File mediaFile = new File(staticResource.getPath());
		File indexFile = new File(mediaFile.getParent(), com.machao.base.commons.FileUtils.obtainFileName(mediaFile) + Type.mpd.getSuffix());
		return FileUtils.readFileToString(indexFile);
	}
}
