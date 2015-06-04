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

	//��������������
	@Override
	public int describeContents() {
		return 0;
	}
	
	//����д�뵽Parcel��
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeInt(age);
	}
	
	//ע�⣺CREATOR�̶�д��
	public final static Parcelable.Creator<Person> CREATOR = new Creator<Person>() {
		//�����л���ȡ����
		@Override
		public Person[] newArray(int size) {
			return new Person[size];
		}
		
		//��Parcel�ж�ȡ���ݣ�ע��Ҫ��д�����ݵ�˳��һ��
		@Override
		public Person createFromParcel(Parcel source) {
			Person person = new Person();
			person.name = source.readString();
			person.age =source.readInt();
			return person;
		}
	};

}
