package com.machao.base.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.machao.base.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
}