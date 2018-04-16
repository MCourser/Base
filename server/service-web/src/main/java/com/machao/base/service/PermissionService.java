package com.machao.base.service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.machao.base.model.Permission;

@Transactional(isolation=Isolation.READ_COMMITTED)
public interface PermissionService extends BaseService<Permission, Integer>{

}
