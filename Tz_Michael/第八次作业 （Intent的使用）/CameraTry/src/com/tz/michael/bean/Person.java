package com.tz.michael.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable{

	private String name;
	private String interest;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", interest=" + interest + ", age="
				+ age + "]";
	}
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(interest);
		dest.writeInt(age);
	}
	
	public final static Parcelable.Creator<Person> CREATOR=new Creator<Person>() {

		public Person createFromParcel(Parcel source) {
			Person p=new Person();
			p.name=source.readString();
			p.interest=source.readString();
			p.age=source.readInt();
			return p;
		}

		public Person[] newArray(int size) {
			return new Person[size];
		}
	};
	
}
