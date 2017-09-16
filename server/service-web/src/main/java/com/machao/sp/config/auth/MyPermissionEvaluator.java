package com.machao.sp.config.auth;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.machao.sp.service.UserService;
import com.machao.sp.utils.SecurityUtils;

@Component
public class MyPermissionEvaluator implements PermissionEvaluator {
	private static final Logger logger = LoggerFactory.getLogger(MyPermissionEvaluator.class);
	
	@Autowired
	private UserService userService;
	
    @Override
    public boolean hasPermission(Authentication authentication, Object resource, Object permission) {
    	logger.info("resource:?, permission:?", resource, permission);
    	return userService.hasPermission(userService.selectByName(SecurityUtils.getPrincipal().getUsername()), resource, permission);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
    	// un support
    	return false;
    }
    
}