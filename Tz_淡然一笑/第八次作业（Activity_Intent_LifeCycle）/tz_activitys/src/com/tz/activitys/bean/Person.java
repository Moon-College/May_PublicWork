package com.tz.activitys.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable{
	
	private String name;
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

	//描述容器的内容
	@Override
	public int describeContents() {
		return 0;
	}
	
	//数据写入到Parcel中
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeInt(age);
	}
	
	//注意：CREATOR固定写法
	public final static Parcelable.Creator<Person> CREATOR = new Creator<Person>() {
		//反序列化获取数组
		@Override
		public Person[] newArray(int size) {
			return new Person[size];
		}
		
		//从Parcel中读取数据，注意要和写入数据的顺序一致
		@Override
		public Person createFromParcel(Parcel source) {
			Person person = new Person();
			person.name = source.readString();
			person.age =source.readInt();
			return person;
		}
	};

}
