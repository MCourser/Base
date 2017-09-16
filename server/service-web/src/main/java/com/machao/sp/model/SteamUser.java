package com.machao.sp.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//@Entity
//@Table(name = "t_steam_user")
public class SteamUser {

	private String steamId;

//	@JoinTable(name="t_user", joinColumns= {@JoinColumn(table="id")}, inverseJoinColumns)
	private User user;

	public String getSteamId() {
		return steamId;
	}

	public void setSteamId(String steamId) {
		this.steamId = steamId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}