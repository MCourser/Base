package com.machao.sp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.machao.sp.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
}