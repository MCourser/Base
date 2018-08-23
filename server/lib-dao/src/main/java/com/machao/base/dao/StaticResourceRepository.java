package com.machao.base.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.machao.base.model.persit.StaticResource;
import com.machao.base.model.persit.StaticResource.Type;

public interface StaticResourceRepository extends JpaRepository<StaticResource, String> {
	
	@Query("select sr from StaticResource sr where sr.type=:type")
	Page<StaticResource> pageByType(@Param("type") Type type, Pageable pageable);
	
	@Query("select sr from StaticResource sr where sr.type=:type and sr.user.id=:userId")
	Page<StaticResource> pageByUserAndType(@Param("userId") int userId, @Param("type") Type type, Pageable pageable);
	
}