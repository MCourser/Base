package com.machao.sp.config.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class AjaxAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler{

	private static final Logger logger = LoggerFactory.getLogger(AjaxAuthFailureHandler.class);
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		logger.info("Authentication failed, {} login failed", request.getParameter("username"));
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed");
	}

}
