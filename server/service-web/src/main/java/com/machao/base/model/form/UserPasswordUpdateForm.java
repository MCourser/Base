package com.machao.base.model.form;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;


public class UserPasswordUpdateForm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Length(min=6, max=30)
	@NotBlank
	private String password;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
