package com.machao.base.service;

import java.util.List;

interface BaseService<T, K> {
	
	T insert(T record);
	
	void deleteByPrimaryKey(K id);
	
	T update(T record);
	
	T selectByPrimaryKey(K id);
	
	List<T> list();
}
