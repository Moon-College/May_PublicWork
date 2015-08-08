package com.junwen.json.model;

public class User {
	private String mName;
	private String mAge;
	private String mUsername;
	private String mPassword;
	
	public User() {
	}
	
	public User(String mName, String mAge, String mUsername, String mPassword) {
		this.mName = mName;
		this.mAge = mAge;
		this.mUsername = mUsername;
		this.mPassword = mPassword;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmAge() {
		return mAge;
	}
	public void setmAge(String mAge) {
		this.mAge = mAge;
	}
	public String getmUsername() {
		return mUsername;
	}
	public void setmUsername(String mUsername) {
		this.mUsername = mUsername;
	}
	public String getmPassword() {
		return mPassword;
	}
	public void setmPassword(String mPassword) {
		this.mPassword = mPassword;
	}
	
	
}
