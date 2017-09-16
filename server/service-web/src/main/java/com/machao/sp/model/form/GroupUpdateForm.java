package com.machao.sp.model.form;

import javax.validation.constraints.NotNull;

public class GroupUpdateForm extends GroupCreateForm {
	@NotNull
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
