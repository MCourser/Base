package com.machao.sp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_role")
public class Role {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable=false, unique=true)
	private String name;

	@Column(nullable=false)
	private String description;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="t_role_permission", joinColumns={@JoinColumn(name="role_id")}, inverseJoinColumns={@JoinColumn(name="permission_id")})
	private List<Permission> permissions;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

}