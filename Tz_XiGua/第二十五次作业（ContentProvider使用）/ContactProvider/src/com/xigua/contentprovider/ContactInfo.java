package com.xigua.contentprovider;

public class ContactInfo {
	//��ϵ������
	private String name;
	//��ϵ������
	private String email;
	//��ϵ�˵绰
	private String phone;
	
	public ContactInfo(){
		
	}
	
	public ContactInfo(String name,String email,String phone){
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
