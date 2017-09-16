package com.machao.base.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.machao.base.dao.RoleRepository;
import com.machao.base.model.Role;
import com.machao.base.service.RoleService;

@Service
public class RoleServiceImp implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role insert(Role record) {
		return this.roleRepository.save(record);
	}

	@Override
	public void deleteByPrimaryKey(Integer id) {
		this.roleRepository.delete(id);
	}

	@Override
	public Role update(Role record) {
		return this.roleRepository.save(record);
	}

	@Override
	public Role selectByPrimaryKey(Integer id) {
		return roleRepository.findOne(id);
	}

	@Override
	public List<Role> list() {
		return roleRepository.findAll();
	}

	
}
