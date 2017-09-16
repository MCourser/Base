package com.machao.sp.model.steam;

public class Asset {

	private int appid;
	private String contextid;
	private String assetid;
	private String classid;
	private String instanceid;
	private String amount;

	public void setAppid(int appid) {
		this.appid = appid;
	}

	public int getAppid() {
		return appid;
	}

	public void setContextid(String contextid) {
		this.contextid = contextid;
	}

	public String getContextid() {
		return contextid;
	}

	public void setAssetid(String assetid) {
		this.assetid = assetid;
	}

	public String getAssetid() {
		return assetid;
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

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAmount() {
		return amount;
	}

}