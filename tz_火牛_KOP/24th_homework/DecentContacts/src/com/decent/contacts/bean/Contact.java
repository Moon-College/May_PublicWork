package com.decent.contacts.bean;

import java.util.List;

public class Contact {
	private int _id;
	private String name;
	private List<String> phoneList;
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<String> phoneList) {
		this.phoneList = phoneList;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

}
