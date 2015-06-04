package com.tz.activitys.bean;

import java.io.Serializable;

/**
 * 学生实体类
 * 
 * @author fcc
 * 
 */
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 名字
	 */
	private String name;
	/**
	 * 年龄
	 */
	private int age;

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

}
