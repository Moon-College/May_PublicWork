package com.tz.michael.bean;

public class ContactInfo {

	private String name;
	private String phoneNum;
	private String email;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ContactInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ContactInfo [name=" + name + ", phoneNum=" + phoneNum
				+ ", email=" + email + "]";
	}
	public ContactInfo(String name, String phoneNum, String email) {
		super();
		this.name = name;
		this.phoneNum = phoneNum;
		this.email = email;
	}
	
}
