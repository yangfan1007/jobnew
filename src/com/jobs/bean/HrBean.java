package com.jobs.bean;

import java.util.Date;

public class HrBean {



private String name;//姓名
private String sex;//性别
private String po;//政治面貌
private Date birth;//出生年月日
private String degree;//学历
private String elevel;//四六级
private String profession;//专业
private String experience;//工作经历
private int id;//简历id
private String school;//毕业学校
private String phone;//电话
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getPo() {
	return po;
}
public void setPo(String po) {
	this.po = po;
}
public Date getBirth() {
	return birth;
}
public void setBirth(Date birth) {
	this.birth = birth;
}
public String getDegree() {
	return degree;
}
public void setDegree(String degree) {
	this.degree = degree;
}
public String getElevel() {
	return elevel;
}
public void setElevel(String elevel) {
	this.elevel = elevel;
}
public String getProfession() {
	return profession;
}
public void setProfession(String profession) {
	this.profession = profession;
}
public String getExperience() {
	return experience;
}
public void setExperience(String experience) {
	this.experience = experience;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getSchool() {
	return school;
}
public void setSchool(String school) {
	this.school = school;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
@Override
public String toString() {
	return "HrBean [name=" + name + ", sex=" + sex + ", po=" + po + ", birth="
			+ birth + ", degree=" + degree + ", elevel=" + elevel
			+ ", profession=" + profession + ", experience=" + experience
			+ ", id=" + id + ", school=" + school + ", phone=" + phone + "]";
}



}
