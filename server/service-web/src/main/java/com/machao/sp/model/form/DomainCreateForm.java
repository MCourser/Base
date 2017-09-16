package com.machao.sp.model.form;

import org.hibernate.validator.constraints.NotBlank;

public class DomainCreateForm {
	@NotBlank
	private String name;

	@NotBlank
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
