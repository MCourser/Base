package com.machao.base.service;

import java.util.Optional;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.machao.base.model.User;

@Transactional(isolation=Isolation.READ_COMMITTED)
public interface UserService extends BasePageableService<User, Integer> {
	
	Optional<User> findByName(String name);
	
	boolean hasPermission(User user, Object resource, Object permission);
	
	boolean hasPermission(Integer id, Object resource, Object permission);
}
