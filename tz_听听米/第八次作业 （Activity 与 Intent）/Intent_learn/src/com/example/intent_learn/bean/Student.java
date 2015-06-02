package com.example.intent_learn.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {
	
	private int id;
	private String name;
	private boolean gender;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", gender=" + gender
				+ "]";
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(name);
		dest.writeInt(gender==true?1:0);
	}
	
	public static final Parcelable.Creator<Student> CREATOR = new Creator<Student>() {
		
		@Override
		public Student[] newArray(int size) {
			
			return new Student[size];
		}
		
		@Override
		public Student createFromParcel(Parcel source) {
			Student student = new Student();
			student.setId(source.readInt());
			student.setName(source.readString());
			student.setGender(source.readInt()==1?true:false);
			return student;
		}
	};

}
