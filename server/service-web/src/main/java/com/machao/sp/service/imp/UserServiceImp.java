package com.machao.sp.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.machao.sp.dao.UserRepository;
import com.machao.sp.model.Permission;
import com.machao.sp.model.Role;
import com.machao.sp.model.User;
import com.machao.sp.service.UserService;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User insert(User record) {
		return this.userRepository.save(record);
	}

	@Override
	public void deleteByPrimaryKey(Integer id) {
		this.userRepository.delete(id);
	}

	@Override
	public User update(User record) {
		return this.userRepository.save(record);
	}
	
	@Override
	public User selectByPrimaryKey(Integer id) {
		return userRepository.findOne(id);
	}

	@Override
	public List<User> list() {
		return userRepository.findAllByOrderByName();
	}

	@Override
	public User selectByName(String name) {
		return userRepository.findByName(name);
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
		User user = selectByPrimaryKey(id);
		if(user == null) return false;
		return hasPermission(user, resource, permission);
	}

	@Override
	public Page<User> list(Pageable pageable) {
		return userRepository.findAllByOrderByName(pageable);
	}
}
