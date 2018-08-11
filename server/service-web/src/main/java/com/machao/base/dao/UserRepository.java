package com.machao.base.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.machao.base.model.persit.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByName(String name);

	List<User> findAllByOrderByName();
	Page<User> findAllByOrderByName(Pageable pageable);
	
	@Query("select u from User u where u.name!=:name")
	List<User> findAllByOrderByNameExceptUser(@Param("name") String name);
	@Query("select u from User u where u.name!=:name")
	Page<User> findAllByOrderByNameExceptUser(@Param("name") String name, Pageable pageable);
}