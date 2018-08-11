package com.machao.base.model.form;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;


public class UserCreateForm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Length(max=20)
	@NotBlank
	private String name;

	@Length(min=6, max=30)
	@NotBlank
	private String password;
	
	@NotEmpty
	private List<Integer> roles;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Integer> getRoles() {
		return roles;
	}

	public void setRoles(List<Integer> roles) {
		this.roles = roles;
	}

}
