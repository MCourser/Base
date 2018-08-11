package com.machao.base.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.machao.base.model.persit.User;

@Transactional(isolation=Isolation.READ_COMMITTED)
public interface UserService extends BaseService<User, Integer> {
	
	Optional<User> findByName(String name);
	
	boolean hasPermission(User user, Object resource, Object permission);
	
	boolean hasPermission(Integer id, Object resource, Object permission);
	
	List<User> findAllByOrderByNameExceptCurrentUser();
	Page<User> findAllByOrderByNameExceptCurrentUser(Pageable pageable);
}
