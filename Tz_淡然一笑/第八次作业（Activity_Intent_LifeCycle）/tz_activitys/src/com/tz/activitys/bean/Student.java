package com.tz.activitys.bean;

import java.io.Serializable;

/**
 * ѧ��ʵ����
 * 
 * @author fcc
 * 
 */
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * ����
	 */
	private String name;
	/**
	 * ����
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
