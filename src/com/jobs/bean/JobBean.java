package com.jobs.bean;

import java.io.Serializable;

public class JobBean implements Serializable {

	
	// ��Ϣ��װ���������
	private String Name;//ְλ��
	private String profession;//����רҵ
	private String address;//������ַ
	private int id;//��Ƹid
	private String require;//����Ҫ��
	private String company;//��˾
	private int tp;//��Ƹ�绰
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
