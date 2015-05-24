package com.example.lesson5;
/** 
 * @author Carlos
 * @version 1.0
 * @updateTime 2015年5月25日 上午1:54:29
 * Description: 
 */
public class User {
	private String name;
	private int sex;
	private int appearance;
	private int head;
	private String hobby;
	
	
	public User(String name,String hobby, int sex, int appearance, int head) {
		super();
		this.name = name;
		this.hobby = hobby;
		this.sex = sex;
		this.appearance = appearance;
		this.head = head;
	}
	
	
	
	public String getHobby() {
		return hobby;
	}



	public void setHobby(String hobby) {
		this.hobby = hobby;
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getAppearance() {
		return appearance;
	}
	public void setAppearance(int appearance) {
		this.appearance = appearance;
	}
	public int getHead() {
		return head;
	}
	public void setHead(int head) {
		this.head = head;
	}
	
	

}
