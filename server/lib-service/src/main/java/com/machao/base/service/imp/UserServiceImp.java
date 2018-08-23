package com.machao.base.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.machao.base.dao.UserRepository;
import com.machao.base.model.persit.Permission;
import com.machao.base.model.persit.Role;
import com.machao.base.model.persit.User;
import com.machao.base.service.UserService;

@Service
public class UserServiceImp extends BaseServiceImp<User, Integer> implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public JpaRepository<User, Integer> obtainJpaRepository() {
		return userRepository;
	}
	
	@Override
	public Optional<User> findByName(String name) {
		return userRepository.findByName(name);
	}
	

	@Override
	public List<User> findAllByOrderByNameExceptUser(String username) {
		return userRepository.findAllByOrderByNameExceptUser(username);
	}

	@Override
	public Page<User> findAllByOrderByNameExceptUser(String username, Pageable pageable) {
		return userRepository.findAllByOrderByNameExceptUser(username, pageable);
	}


	@Override
	public boolean hasPermission(User user, Object resource, Object permission) {
		if(user == null) return false;
		if(user.getRoles() != null && !user.getRoles().isEmpty()) {
			for(Role role : user.getRoles()) {
				if(role.getPermissions() != null && !role.getPermissions().isEmpty()) {
					for(Permission perm : role.getPermissions()) {
						if(resource.equals(perm.getResource()) && permission.equals(perm.getValue())) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean hasPermission(Integer id, Object resource, Object permission) {
		Optional<User> user = findById(id);
		if(!user.isPresent()) return false;
		return hasPermission(user.get(), resource, permission);
	}
}
