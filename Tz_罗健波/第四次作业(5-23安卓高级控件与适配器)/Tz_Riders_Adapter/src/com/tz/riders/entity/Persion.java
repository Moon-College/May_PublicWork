package com.tz.riders.entity;

import java.io.Serializable;

public class Persion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2217988819907531913L;
	private String pName;
	private String hobby;
	private int pGender;
	private int picture_id;
	private int number;

	public Persion() {
	}

	public Persion(String name, String hobby, int gender, int picture_id,
			int number) {
		this.pName = name;
		this.hobby = hobby;
		this.pGender = gender;
		this.picture_id = picture_id;
		this.number = number;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getpGender() {
		return pGender;
	}

	public void setpGender(int pGender) {
		this.pGender = pGender;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public int getPicture_id() {
		return picture_id;
	}

	public void setPicture_id(int picture_id) {
		this.picture_id = picture_id;
	}

}
