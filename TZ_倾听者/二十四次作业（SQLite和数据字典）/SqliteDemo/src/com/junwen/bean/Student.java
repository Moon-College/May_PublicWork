package com.junwen.bean;

public class Student {

	private String name; //姓名
	private int age; //年龄
	private String classes; //班级
	private String professional; //专业
	public Student(String name, int age, String classes, String professional) {
		super();
		this.name = name;
		this.age = age;
		this.classes = classes;
		this.professional = professional;
	}
	
	public Student() {
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
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public String getProfessional() {
		return professional;
	}
	public void setProfessional(String professional) {
		this.professional = professional;
	}
	
}
