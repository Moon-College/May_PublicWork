package com.mytz_listadapter;

public class UserBean {
	public int getHeader() {
		return header;
	}
	public void setHeader(int header) {
		this.header = header;
	}
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
	public int getFace() {
		return face;
	}
	public void setFace(int face) {
		this.face = face;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public UserBean(int header, String name, String sex, int face,
			String interest) {
		super();
		this.header = header;
		this.name = name;
		this.sex = sex;
		this.face = face;
		this.interest = interest;
	}
	private int header;
	private String name;
	private String sex;
	private int face;
	private String interest;
}
