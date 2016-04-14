package com.jobs.bean;

import java.io.Serializable;

public class JobBean implements Serializable {

	
	// 信息封装到这个类里
	private String Name;//职位名
	private String profession;//所需专业
	private String address;//工作地址
	private int id;//招聘id
	private String require;//工作要求
	private String company;//公司
	private int tp;//招聘电话
	public JobBean() {
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRequire() {
		return require;
	}
	public void setRequire(String require) {
		this.require = require;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getTp() {
		return tp;
	}
	public void setTp(int tp) {
		this.tp = tp;
	}
	@Override
	public String toString() {
		return "JobBean [Name=" + Name + ", profession=" + profession
				+ ", address=" + address + ", id=" + id + ", require="
				+ require + ", company=" + company + ", tp=" + tp + "]";
	}
	




}
