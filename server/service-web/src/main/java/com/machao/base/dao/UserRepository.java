package com.machao.base.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.machao.base.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByName(String name);

	List<User> findAllByOrderByName();
	
	Page<User> findAllByOrderByName(Pageable pageable);
}