package com.machao.sp.config.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AjaxLogoutSuccessHandler  extends AbstractAuthenticationTargetUrlRequestHandler
		implements LogoutSuccessHandler{

	private static final Logger logger = LoggerFactory.getLogger(AjaxLogoutSuccessHandler.class);
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		logger.info("Logout success");
		response.setStatus(HttpServletResponse.SC_OK);
	}

}
