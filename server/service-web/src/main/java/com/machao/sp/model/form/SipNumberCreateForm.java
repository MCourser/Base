package com.machao.sp.model.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class SipNumberCreateForm {
	@NotBlank
	private String number;

	@NotNull
	private Integer domainId;

	@NotNull
	private Integer groupId;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getDomainId() {
		return domainId;
	}

	public void setDomainId(Integer domainId) {
		this.domainId = domainId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
}
