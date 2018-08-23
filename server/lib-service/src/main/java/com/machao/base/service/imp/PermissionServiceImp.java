package com.machao.base.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.machao.base.dao.PermissionRepository;
import com.machao.base.model.persit.Permission;
import com.machao.base.service.PermissionService;

@Service
public class PermissionServiceImp extends BaseServiceImp<Permission, Integer> implements PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;
	
	@Override
	public JpaRepository<Permission, Integer> obtainJpaRepository() {
		return permissionRepository;
	}
}
