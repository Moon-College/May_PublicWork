package cn.ysh.bean;

import java.util.List;

public class Teacher extends Person{
	private String name;
	private int age;
	private String hobby;
	private String duty;
	private List<Student> students;
	
	public Teacher(){
		
	}
	
	public Teacher(String name, int age, String hobby, String duty,
			List<Student> students) {
		super();
		this.name = name;
		this.age = age;
		this.hobby = hobby;
		this.duty = duty;
		this.students = students;
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

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
}
