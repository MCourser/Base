package com.machao.sp.model.steam;

import java.util.List;

public class Inventory {

	private List<Asset> assets;
	private List<Descriptions> descriptions;
	private int more_items;
	private String last_assetid;
	private int total_inventory_count;
	private int success;
	private int rwgrsn;

	public void setAssets(List<Asset> assets) {
		this.assets = assets;
	}

	public List<Asset> getAssets() {
		return assets;
	}

	public void setDescriptions(List<Descriptions> descriptions) {
		this.descriptions = descriptions;
	}

	public List<Descriptions> getDescriptions() {
		return descriptions;
	}

	public void setMore_items(int more_items) {
		this.more_items = more_items;
	}

	public int getMore_items() {
		return more_items;
	}

	public void setLast_assetid(String last_assetid) {
		this.last_assetid = last_assetid;
	}

	public String getLast_assetid() {
		return last_assetid;
	}

	public void setTotal_inventory_count(int total_inventory_count) {
		this.total_inventory_count = total_inventory_count;
	}

	public int getTotal_inventory_count() {
		return total_inventory_count;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public int getSuccess() {
		return success;
	}

	public void setRwgrsn(int rwgrsn) {
		this.rwgrsn = rwgrsn;
	}

	public int getRwgrsn() {
		return rwgrsn;
	}

}