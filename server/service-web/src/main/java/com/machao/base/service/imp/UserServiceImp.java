package com.machao.base.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.machao.base.dao.UserRepository;
import com.machao.base.model.Permission;
import com.machao.base.model.Role;
import com.machao.base.model.User;
import com.machao.base.service.UserService;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User insert(User record) {
		return this.userRepository.save(record);
	}
	
	@Override
	public void deleteById(Integer id) {
		this.userRepository.deleteById(id);
	}

	@Override
	public User update(User record) {
		return this.userRepository.save(record);
	}
	
	@Override
	public Optional<User> findById(Integer id) {
		return userRepository.findById(id);
	}

	@Override
	public Page<User> page(Pageable pageable) {
		return userRepository.findAll(pageable);
	}
	
	@Override
	public List<User> list() {
		return userRepository.findAllByOrderByName();
	}
	
	@Override
	public Page<User> list(Pageable pageable) {
		return userRepository.findAllByOrderByName(pageable);
	}

	@Override
	public Optional<User> findByName(String name) {
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
		Optional<User> user = findById(id);
		if(!user.isPresent()) return false;
		return hasPermission(user.get(), resource, permission);
	}

}
