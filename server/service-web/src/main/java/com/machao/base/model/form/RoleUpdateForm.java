package com.machao.base.model.form;

import javax.validation.constraints.NotNull;

public class RoleUpdateForm extends RoleCreateForm {

	@NotNull
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
}
