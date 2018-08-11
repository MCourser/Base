package com.machao.base.statis_resource.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Component;

import com.machao.base.mq.static_resource.audio.response.AudioPlayListResponse;
import com.machao.base.mq.static_resource.image.response.ImageResizingResponse;
import com.machao.base.mq.static_resource.video.response.VideoPlayListResponse;
import com.machao.base.statis_resource.exception.ResourceNotFoundException;

@Component
public class StaticResourcePathUtils {
	
	@Autowired
    private ServletContext servletContext;
	
	@Autowired
    private ServerProperties serverProperties;

	private String obtainContextPath() {
		StringBuilder sb = new StringBuilder();
		sb.append(serverProperties.getSsl().isEnabled()?"https://":"http://").append(serverProperties.getAddress().getHostAddress()).append(":").append(serverProperties.getPort()).append(serverProperties.getServlet().getContextPath());
		return sb.toString();
	}
	
	public void bindPath4ImageResizingResponse(ImageResizingResponse imageResizingResponse) {
		String uuid = UUID.randomUUID().toString();
		String url = obtainContextPath() + "/image/" + uuid;
		imageResizingResponse.setUrl(url);
		
		this.setAttribute(uuid, imageResizingResponse);
	}
	
	public void bindPath4AudioPlayListResponse(AudioPlayListResponse audioPlayListResponse) {
		String uuid = UUID.randomUUID().toString();
		String url = obtainContextPath() + "/audio/" + uuid + "/";
		audioPlayListResponse.setUrl(url);
		
		this.setAttribute(uuid, audioPlayListResponse);
	}
	
	public String bindPath4AudioPlayListM3u8File(String uuid, File file) throws IOException {
		StringBuffer newLines = new StringBuffer();
		List<String> lines = FileUtils.readLines(file);
		lines.forEach(line->{
			if(!line.startsWith("#")) {
				String tsUrl = obtainContextPath() + "/audio/" + uuid + "/" + line;
				newLines.append(tsUrl).append('\n');
			} else {
				newLines.append(line).append('\n');
			}
		});
		return newLines.toString();
	}
	
	public void bindPath4VideoPlayListResponse(VideoPlayListResponse videoPlayListResponse) {
		String uuid = UUID.randomUUID().toString();
		String url = obtainContextPath() + "/video/" + uuid + "/";
		videoPlayListResponse.setUrl(url);
		
		this.setAttribute(uuid, videoPlayListResponse);
	}
	
	public String bindPath4VideoPlayListM3u8File(String uuid, File file) throws IOException {
		StringBuffer newLines = new StringBuffer();
		List<String> lines = FileUtils.readLines(file);
		lines.forEach(line->{
			if(!line.startsWith("#")) {
				String tsUrl = obtainContextPath() + "/video/" + uuid + "/" + line;
				newLines.append(tsUrl).append('\n');
			} else {
				newLines.append(line).append('\n');
			}
		});
		return newLines.toString();
	}
	
	public void setAttribute(String uuid, Object obj) {
		this.servletContext.setAttribute(uuid, obj);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String uuid, Class<T> clazz) {
		T retT =  (T) servletContext.getAttribute(uuid);
		if(retT == null) throw new ResourceNotFoundException();
		return retT;
	}
}
