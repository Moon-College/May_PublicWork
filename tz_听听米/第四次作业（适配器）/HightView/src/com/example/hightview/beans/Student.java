package com.example.hightview.beans;

public class Student {
	
	private String name;
	private int faceValue;
	private int ImageId;
	private int sex;//0 女 1 男
	private String hobby;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFaceValue() {
		return faceValue;
	}
	public void setFaceValue(int faceValue) {
		this.faceValue = faceValue;
	}
	public int getImageId() {
		return ImageId;
	}
	public void setImageId(int imageId) {
		ImageId = imageId;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	

}
