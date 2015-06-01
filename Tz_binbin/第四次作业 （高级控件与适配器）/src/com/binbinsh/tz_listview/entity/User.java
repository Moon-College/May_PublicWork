package com.binbinsh.tz_listview.entity;

/**
 * 要求1，每个条目是同学的头像+网名+性别+颜值+爱好 
 * 要求2，条目数不少于10 ，男生背景用蓝色，妹子背景用红色 
 * 要求3，点击对应的条目，移除该条目
 * 
 * @author binbinsh
 * 
 */
public class User {
	private final static String SEX_BOY="boy";
	private final static String SEX_GIRL="girl";
			

	public User(String name, String thumbnail, int faceValue, String sex, String hobbies) {
		super();
		this.name = name;
		this.thumbnail = thumbnail;
		this.faceValue = faceValue;
		this.hobbies = hobbies;
		this.sex = sex;
	}
	private Long id;
	private String name;
	private String thumbnail;
	private int faceValue;
	private String hobbies;
	private String sex=SEX_BOY;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public int getFaceValue() {
		return faceValue;
	}
	public void setFaceValue(int faceValue) {
		this.faceValue = faceValue;
	}
	public String getHobbies() {
		return hobbies;
	}
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
	
	
}
