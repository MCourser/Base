package com.machao.base.service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.machao.base.model.Role;

@Transactional(isolation=Isolation.READ_COMMITTED)
public interface RoleService extends BaseService<Role, Integer>{

}
