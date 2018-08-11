package com.machao.base.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<T, K> {
	
	T insert(T entity);
	
	void deleteById(K id);
	
	T update(T entity);
	
	Optional<T> findById(K id);
	
	List<T> list();
	
	Page<T> page(Pageable pageable);
}
