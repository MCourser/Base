package com.machao.base.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.machao.base.service.BaseService;

public abstract class BaseServiceImp<T, K> implements BaseService<T, K> {
	
	public abstract JpaRepository<T, K> obtainJpaRepository();

	@Override
	public T insert(T entity) {
		return obtainJpaRepository().save(entity);
	}

	@Override
	public void deleteById(K id) {
		obtainJpaRepository().deleteById(id);
	}

	@Override
	public T update(T entity) {
		return obtainJpaRepository().save(entity);
	}

	@Override
	public Optional<T> findById(K id) {
		return obtainJpaRepository().findById(id);
	}

	@Override
	public List<T> list() {
		return obtainJpaRepository().findAll();
	}

	@Override
	public Page<T> page(Pageable pageable) {
		return obtainJpaRepository().findAll(pageable);
	}
}
