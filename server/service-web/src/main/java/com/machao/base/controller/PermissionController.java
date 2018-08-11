package com.machao.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.machao.base.model.persit.Permission;
import com.machao.base.service.PermissionService;

import io.swagger.annotations.ApiOperation;

@RestController
@RestControllerAdvice 
@RequestMapping("/permission")
public class PermissionController extends BaseController{

	@Autowired
	private PermissionService permissionService;
	
	@ApiOperation("list permissions")
	@PreAuthorize("authenticated and hasPermission('/permission/', 'permission:list')")
	@GetMapping("/")
	public ResponseEntity<List<Permission>> list() {
		return ResponseEntity.ok(permissionService.list());
	}
	
}
