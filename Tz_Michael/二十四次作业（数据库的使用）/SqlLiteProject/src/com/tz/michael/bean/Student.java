package com.tz.michael.bean;

public class Student {

	private String name;
	private int age;
	private int classId;
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
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", classId="
				+ classId + "]";
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(String name, int age, int classId) {
		super();
		this.name = name;
		this.age = age;
		this.classId = classId;
	}
}
