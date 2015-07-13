package com.tz.michael.bean;

public class ClassBean {

	private String name;
	private int studentTotalNum;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStudentTotalNum() {
		return studentTotalNum;
	}
	public void setStudentTotalNum(int studentTotalNum) {
		this.studentTotalNum = studentTotalNum;
	}
	@Override
	public String toString() {
		return "ClassBean [name=" + name + ", studentTotalNum="
				+ studentTotalNum + "]";
	}
	public ClassBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ClassBean(String name, int studentTotalNum) {
		super();
		this.name = name;
		this.studentTotalNum = studentTotalNum;
	}
	
}
