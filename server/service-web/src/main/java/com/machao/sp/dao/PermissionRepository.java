package com.machao.sp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.machao.sp.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
	
}