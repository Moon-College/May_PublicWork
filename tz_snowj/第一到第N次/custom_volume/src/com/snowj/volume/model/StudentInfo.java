package com.snowj.volume.model;

import android.graphics.drawable.Drawable;


public class StudentInfo {

	//头像+网名+性别+颜值+爱好
	private Drawable headPortrait;
	private String nickName;
	private String gender;
	private String colorValue;
	private String interest;


	public Drawable getHeadPortrait() {
		return headPortrait;
	}
	public void setHeadPortrait(Drawable headPortrait) {
		this.headPortrait = headPortrait;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getColorValue() {
		return colorValue;
	}
	public void setColorValue(String colorValue) {
		this.colorValue = colorValue;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	
	
}
