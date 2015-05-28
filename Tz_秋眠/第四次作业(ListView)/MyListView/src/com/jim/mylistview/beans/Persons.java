package com.jim.mylistview.beans;

/**
 * 好友列表的实体类
 * 
 * @author fb
 * @time 2015.05.25
 */
public class Persons {

	private String name;// 网名
	private String gender;// 性别
	private String hobby;// 爱好
	private int appearance;// 颜值
	private int img;// 头像

	/**
	 * 
	 * @param name
	 *            网名
	 * @param gender
	 *            性别
	 * @param hobby
	 *            爱好
	 * @param appearance
	 *            颜值
	 * @param img
	 *            头像
	 */
	public Persons(String name, String gender, String hobby, int appearance,
			int img) {
		super();
		this.name = name;
		this.gender = gender;
		this.hobby = hobby;
		this.appearance = appearance;
		this.img = img;
	}

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public String getHobby() {
		return hobby;
	}

	public int getAppearance() {
		return appearance;
	}

	public int getImg() {
		return img;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public void setAppearance(int appearance) {
		this.appearance = appearance;
	}

	public void setImg(int img) {
		this.img = img;
	}
}
