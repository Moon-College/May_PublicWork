package com.rocy.my;

public class Student {
     public String name;
     
     public int face;
     
     public int sex;//0表示男 1表示女
     
     
     public String  age;//年龄

     
	public Student(String name, int face, int sex, String age) {
		super();
		this.name = name;
		this.face = face;
		this.sex = sex;
		this.age = age;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getFace() {
		return face;
	}


	public void setFace(int face) {
		this.face = face;
	}


	public int getSex() {
		return sex;
	}


	public void setSex(int sex) {
		this.sex = sex;
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}
     
}
