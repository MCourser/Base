package com.machao.base.model.form;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;


public class RoleCreateForm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Length(max=20)
	@NotBlank
	private String name;

	@Length(max=255)
	@NotBlank
	private String description;
	
	@NotEmpty
	private List<Integer> permissions;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Integer> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Integer> permissions) {
		this.permissions = permissions;
	}

}
