package com.machao.sp.model.form;

import org.hibernate.validator.constraints.NotBlank;

public class GroupCreateForm {
	@NotBlank
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
