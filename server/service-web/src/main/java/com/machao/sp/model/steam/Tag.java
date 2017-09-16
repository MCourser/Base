package com.machao.sp.model.steam;

public class Tag {

	private String category;
	private String internal_name;
	private String localized_category_name;
	private String localized_tag_name;
	private String color;

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public void setInternal_name(String internal_name) {
		this.internal_name = internal_name;
	}

	public String getInternal_name() {
		return internal_name;
	}

	public void setLocalized_category_name(String localized_category_name) {
		this.localized_category_name = localized_category_name;
	}

	public String getLocalized_category_name() {
		return localized_category_name;
	}

	public void setLocalized_tag_name(String localized_tag_name) {
		this.localized_tag_name = localized_tag_name;
	}

	public String getLocalized_tag_name() {
		return localized_tag_name;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getColor() {
		return color;
	}

}