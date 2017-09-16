package com.machao.sp.model.steam;

import java.util.List;

public class Descriptions {

	private int appid;
	private String classid;
	private String instanceid;
	private int currency;
	private String background_color;
	private String icon_url;
	private String icon_url_large;
	private List<Description> descriptions;
	private int tradable;
	private String name;
	private String name_color;
	private String type;
	private String market_name;
	private String market_hash_name;
	private int commodity;
	private int market_tradable_restriction;
	private int market_marketable_restriction;
	private int marketable;
	private List<Tag> tags;

	public void setAppid(int appid) {
		this.appid = appid;
	}

	public int getAppid() {
		return appid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	public String getClassid() {
		return classid;
	}

	public void setInstanceid(String instanceid) {
		this.instanceid = instanceid;
	}

	public String getInstanceid() {
		return instanceid;
	}

	public void setCurrency(int currency) {
		this.currency = currency;
	}

	public int getCurrency() {
		return currency;
	}

	public void setBackground_color(String background_color) {
		this.background_color = background_color;
	}

	public String getBackground_color() {
		return background_color;
	}

	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}

	public String getIcon_url() {
		return icon_url;
	}

	public void setIcon_url_large(String icon_url_large) {
		this.icon_url_large = icon_url_large;
	}

	public String getIcon_url_large() {
		return icon_url_large;
	}

	public void setDescriptions(List<Description> descriptions) {
		this.descriptions = descriptions;
	}

	public List<Description> getDescriptions() {
		return descriptions;
	}

	public void setTradable(int tradable) {
		this.tradable = tradable;
	}

	public int getTradable() {
		return tradable;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName_color(String name_color) {
		this.name_color = name_color;
	}

	public String getName_color() {
		return name_color;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setMarket_name(String market_name) {
		this.market_name = market_name;
	}

	public String getMarket_name() {
		return market_name;
	}

	public void setMarket_hash_name(String market_hash_name) {
		this.market_hash_name = market_hash_name;
	}

	public String getMarket_hash_name() {
		return market_hash_name;
	}

	public void setCommodity(int commodity) {
		this.commodity = commodity;
	}

	public int getCommodity() {
		return commodity;
	}

	public void setMarket_tradable_restriction(int market_tradable_restriction) {
		this.market_tradable_restriction = market_tradable_restriction;
	}

	public int getMarket_tradable_restriction() {
		return market_tradable_restriction;
	}

	public void setMarket_marketable_restriction(int market_marketable_restriction) {
		this.market_marketable_restriction = market_marketable_restriction;
	}

	public int getMarket_marketable_restriction() {
		return market_marketable_restriction;
	}

	public void setMarketable(int marketable) {
		this.marketable = marketable;
	}

	public int getMarketable() {
		return marketable;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<Tag> getTags() {
		return tags;
	}

}