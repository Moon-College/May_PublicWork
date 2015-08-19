package cn.ysh.bean;

public class Student extends Person{

	private String name;
	private int age;
	private int gender;
	private int sid;
	public Student(String name, int age, int gender, int sid) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.sid = sid;
	}
	
	public Student() {
		super();
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

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}
	
}
