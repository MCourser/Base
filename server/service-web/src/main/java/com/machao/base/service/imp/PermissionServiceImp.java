package com.machao.base.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.machao.base.dao.PermissionRepository;
import com.machao.base.model.Permission;
import com.machao.base.service.PermissionService;

@Service
public class PermissionServiceImp implements PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public Permission insert(Permission entity) {
		return permissionRepository.save(entity);
	}

	@Override
	public void deleteById(Integer id) {
		this.permissionRepository.deleteById(id);
	}

	@Override
	public Permission update(Permission entity) {
		return permissionRepository.save(entity);
	}

	@Override
	public Optional<Permission> findById(Integer id) {
		return permissionRepository.findById(id);
	}

	@Override
	public List<Permission> list() {
		return permissionRepository.findAll();
	}

	@Override
	public Page<Permission> page(Pageable pageable) {
		return permissionRepository.findAll(pageable);
	}

}
