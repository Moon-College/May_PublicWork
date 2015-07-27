package com.junwen.aidl;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable{

	private String name;
	private int age;
	private String sex;
	
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeInt(age);
		dest.writeString(sex);
	}
	public static final Parcelable.Creator<Student> CREATOR = new Creator<Student>() {
		
		@Override
		public Student[] newArray(int size) {
			return null;
		}
		
		@Override
		public Student createFromParcel(Parcel source) {
			Student stu = new Student();
			stu.name = source.readString();
			stu.age = source.readInt();
			stu.sex = source.readString();
			return stu;
		}
	};
	
}
