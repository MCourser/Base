package com.machao.base.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.machao.base.dao.RoleRepository;
import com.machao.base.model.Role;
import com.machao.base.service.RoleService;

@Service
public class RoleServiceImp implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role insert(Role entity) {
		return roleRepository.save(entity);
	}

	@Override
	public void deleteById(Integer id) {
		this.roleRepository.deleteById(id);
	}

	@Override
	public Role update(Role entity) {
		return roleRepository.save(entity);
	}

	@Override
	public Optional<Role> findById(Integer id) {
		return roleRepository.findById(id);
	}

	@Override
	public List<Role> list() {
		return roleRepository.findAll();
	}

	@Override
	public Page<Role> page(Pageable pageable) {
		return roleRepository.findAll(pageable);
	}


	
}
