package com.machao.base.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.machao.base.model.persit.StaticResource;
import com.machao.base.model.persit.StaticResource.Type;
import com.machao.base.model.persit.User;

@Transactional(isolation=Isolation.READ_COMMITTED)
public interface StaticResourceService extends BaseService<StaticResource, String>{
	
	Page<StaticResource> pageByType(Type type, Pageable pageable);
	Page<StaticResource> pageByUserAndType(User user, Type type, Pageable pageable);
	
	String getStaticResourcePath(StaticResource staticResource);

}
