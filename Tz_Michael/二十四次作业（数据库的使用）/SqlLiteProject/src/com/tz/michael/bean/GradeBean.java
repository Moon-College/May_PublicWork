package com.tz.michael.bean;

public class GradeBean {

	private String name;
	private String className;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	@Override
	public String toString() {
		return "GradeBean [name=" + name + ", className=" + className + "]";
	}
	public GradeBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GradeBean(String name, String className) {
		super();
		this.name = name;
		this.className = className;
	}
}
