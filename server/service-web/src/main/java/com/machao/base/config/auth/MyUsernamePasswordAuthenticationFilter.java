package com.machao.base.config.auth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		if(request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE) ||request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)){
            try {
				BufferedReader payload = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
				String line = null;
				StringBuilder sb = new StringBuilder();
				while ((line = payload.readLine()) != null) {
					sb.append(line);
				}
				
				ObjectMapper mapper = new ObjectMapper();
		        Map<String, String> map = mapper.readValue(sb.toString(), new TypeReference<HashMap<String,String>>(){});  
            	request.setAttribute("username", map.get("username"));
            	request.setAttribute("password", map.get("password"));	

            }catch (IOException e) {
            	e.printStackTrace();
            }
        }
		return super.attemptAuthentication(request, response);
	}

	@Override
	protected String obtainPassword(HttpServletRequest request) {
		if(request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE) ||request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)){
			return (String) request.getAttribute("password");
        }
		return super.obtainPassword(request);
	}

	@Override
	protected String obtainUsername(HttpServletRequest request) {
		if(request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE) ||request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)){
			return (String) request.getAttribute("username");
        }
		return super.obtainUsername(request);
	}

}
