package com.machao.base.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

interface BasePageableService<T, K> extends BaseService<T, K> {
	Page<T> list(Pageable pageable);
}
