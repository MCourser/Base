package com.machao.base.service;

import com.machao.base.model.User;

public interface UserService extends BasePageableService<User, Integer> {
	
	User selectByName(String name);
	
	boolean hasPermission(User user, Object resource, Object permission);
	
	boolean hasPermission(Integer id, Object resource, Object permission);
}
