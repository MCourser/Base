package com.machao.base.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.machao.base.model.exception.UnauthorizedException;

public class SecurityUtils {
	
	public static org.springframework.security.core.userdetails.User getPrincipal() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();		
		if (authentication == null) throw new UnauthorizedException();
		if( authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
			return (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
		}
		return null;
	}

}