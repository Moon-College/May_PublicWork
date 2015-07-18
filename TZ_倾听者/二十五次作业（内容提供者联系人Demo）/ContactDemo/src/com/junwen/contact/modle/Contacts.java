package com.junwen.contact.modle;
/**
 * 联系人实体类
 * @author June
 *
 */
public class Contacts {

	private int id; //联系人ID
	private String name; //联系人名字
	private String phoneNumber; //联系人号码
	private String email; //联系人email
	
	public Contacts() {
	}
	public Contacts(int id, String name, String phoneNumber, String email) {
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
