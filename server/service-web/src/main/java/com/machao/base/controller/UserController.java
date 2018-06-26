package com.machao.base.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.machao.base.exception.ResourceNotFoundException;
import com.machao.base.exception.UserNotFoundException;
import com.machao.base.model.Role;
import com.machao.base.model.User;
import com.machao.base.model.form.UserCreateForm;
import com.machao.base.model.form.UserUpdateForm;
import com.machao.base.service.RoleService;
import com.machao.base.service.UserService;
import com.machao.base.utils.SecurityUtils;

import io.swagger.annotations.ApiOperation;

@RestController
@RestControllerAdvice 
@RequestMapping("/user")
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@ApiOperation(value = "Current User", notes = "load current user")
	@PreAuthorize("authenticated")
	@GetMapping("/me")
	public ResponseEntity<User> currnet() {
		if(SecurityUtils.getPrincipal() == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
		return this.userService.findByName(SecurityUtils.getPrincipal().getUsername()).map(user->{
			return ResponseEntity.ok(user);
		}).orElseThrow(UserNotFoundException::new);
	}
	
	@ApiOperation(value = "List Users", notes = "list system users with page")
	@PreAuthorize("authenticated and hasPermission('/user/', 'user:list')")
	@GetMapping("/")
	public ResponseEntity<Page<User>> list(Pageable pageable) {
		return ResponseEntity.ok(userService.list(pageable));
	}
	
	@ApiOperation(value = "List Users", notes = "list system users")
	@PreAuthorize("authenticated and hasPermission('/user/', 'user:list')")
	@GetMapping("/all")
	public ResponseEntity<List<User>> listAll() {
		return ResponseEntity.ok(userService.list());
	}
	
	@ApiOperation(value = "Load User", notes = "load a user by id")
	@PreAuthorize("authenticated and hasPermission('/user/{id}', 'user:load')")
	@GetMapping("/{id}")
	public ResponseEntity<User> load(@PathVariable int id) {
		return this.userService.findById(id).map(user->{
			return ResponseEntity.ok(user);
		}).orElseThrow(ResourceNotFoundException::new);
	}
	
	@ApiOperation(value = "Add User", notes = "add user")
	@PreAuthorize("authenticated and hasPermission('/user/', 'user:add')")
	@PostMapping("/")
	public ResponseEntity<User> create(@Valid @RequestBody UserCreateForm userCreateForm, Errors errors) {
		super.checkRequestParams(errors);
		
		User user = new User();
		user.setName(userCreateForm.getName());
		user.setPassword(passwordEncoder.encode(userCreateForm.getPassword()));

		Set<Role> roleList = new HashSet<Role>();
		userCreateForm.getRoles().forEach(roleId->{
			this.roleService.findById(roleId).ifPresent(role->{
				roleList.add(role);
			});
		});
		user.setRoles(roleList);
		
		User savedUser = userService.insert(user);
		
		return ResponseEntity.ok(savedUser);
	}


	@ApiOperation(value = "Update User", notes = "update user")
	@PreAuthorize("authenticated and hasPermission('/user/', 'user:update')")
	@PutMapping("/")
	public ResponseEntity<User> update(@Valid @RequestBody UserUpdateForm userUpdateForm, Errors errors) {
		super.checkRequestParams(errors);
		
		return this.userService.findById(userUpdateForm.getId()).map(user->{
			user.setName(userUpdateForm.getName());
			
			if(!StringUtils.isEmpty(userUpdateForm.getPassword())) {
				user.setPassword(passwordEncoder.encode(userUpdateForm.getPassword()));
			} 
			
			Set<Role> roleList = new HashSet<Role>();
			userUpdateForm.getRoles().forEach(roleId->{
				this.roleService.findById(roleId).ifPresent(role->{
					roleList.add(role);
				});
			});
			user.setRoles(roleList);
			
			User savedUser = userService.update(user);
			
			return ResponseEntity.ok(savedUser);
		}).orElseThrow(UserNotFoundException::new);
	}

	@ApiOperation(value = "Delete User", notes = "delete user")
	@PreAuthorize("authenticated and hasPermission('/user/{id}', 'user:delete')")
	@DeleteMapping("/{id}")
	public ResponseEntity<User> delete(@PathVariable int id) {
		return this.userService.findById(id).map(user->{
			userService.deleteById(id);
			return ResponseEntity.ok(user);
		}).orElseThrow(UserNotFoundException::new);
	}
}
