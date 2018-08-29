package com.machao.base.service.imp;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.machao.base.dao.StaticResourceRepository;
import com.machao.base.model.exception.config.StaticResourceConfig;
import com.machao.base.model.persit.StaticResource;
import com.machao.base.model.persit.StaticResource.Type;
import com.machao.base.model.persit.User;
import com.machao.base.service.StaticResourceService;

@Service
public class StaticResourceServiceImp extends BaseServiceImp<StaticResource, String> implements StaticResourceService {
	
	@Autowired
	private StaticResourceConfig staticResourceConfig;

	@Autowired
	private StaticResourceRepository staticResourceRepository;
	
	@Override
	public JpaRepository<StaticResource, String> obtainJpaRepository() {
		return staticResourceRepository;
	}
	
	@Override
	public StaticResource insert(StaticResource entity) {
		StaticResource staticResource = super.insert(entity);
		staticResource.setPath(getStaticResourcePath(staticResource));
		return staticResource;
	}

	@Override
	public StaticResource update(StaticResource entity) {
		StaticResource staticResource = super.update(entity);
		staticResource.setPath(getStaticResourcePath(staticResource));
		return staticResource;
	}

	@Override
	public Optional<StaticResource> findById(String id) {
		return super.findById(id).map(staticResource->{
			staticResource.setPath(getStaticResourcePath(staticResource));
			return staticResource;
		});
	}

	@Override
	public List<StaticResource> list() {
		List<StaticResource> list = super.list();
		list.forEach(staticResource->{
			staticResource.setPath(getStaticResourcePath(staticResource));
		});
		return list;
	}
	
	@Override
	public Page<StaticResource> pageByType(Type type, Pageable pageable) {
		return staticResourceRepository.pageByType(type, pageable);
	}
	
	@Override
	public Page<StaticResource> pageByUserAndType(User user, Type type, Pageable pageable) {
		return staticResourceRepository.pageByUserAndType(user.getId(), type, pageable);
	}

	
	@Override
	public Page<StaticResource> page(Pageable pageable) {
		Page<StaticResource> page = super.page(pageable);
		page.getContent().forEach(staticResource->{
			staticResource.setPath(getStaticResourcePath(staticResource));
		});
		return page;
	}

	@Override
	public String getStaticResourcePath(StaticResource staticResource) {
		return new File(staticResourceConfig.getRootPath(), staticResource.getRelativePath()).getAbsolutePath();
	}

}
