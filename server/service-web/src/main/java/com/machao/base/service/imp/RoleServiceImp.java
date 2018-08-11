package com.machao.base.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.machao.base.dao.RoleRepository;
import com.machao.base.model.persit.Role;
import com.machao.base.service.RoleService;

@Service
public class RoleServiceImp extends BaseServiceImp<Role, Integer> implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public JpaRepository<Role, Integer> obtainJpaRepository() {
		return roleRepository;
	}
}
