package com.tz.michael.bean;
/**
 * 模板类
 * @author szm
 *
 */
public class Students {

	/**头像*/
	private int potrait;
	/**姓名*/
	private String name;
	/**年龄*/
	private int age;
	/**爱好*/
	private String interest;
	/**性别*/
	private int gender;
	public Students() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getPotrait() {
		return potrait;
	}
	public void setPotrait(int potrait) {
		this.potrait = potrait;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "Students [potrait=" + potrait + ", name=" + name + ", age="
				+ age + ", interest=" + interest + ", gender=" + gender + "]";
	}
	
}
