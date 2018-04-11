package com.machao.base.config.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AjaxAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

	private static final Logger logger = LoggerFactory.getLogger(AjaxAuthSuccessHandler.class);
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		logger.info("Authentication success, {} login successfully", request.getParameter("username"));
		response.setStatus(HttpServletResponse.SC_OK);
	}

}
