package com.hq.service.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created on 2015年8月14日下午3:34:56 MyDog.java<br/>
 * Author ZhuHequn 1363790125@qq.com <br/>
 * version 1.0 TODO
 */
public class MyDog implements Parcelable {
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

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(name);
		dest.writeInt(age);

	}

	public static final Parcelable.Creator<MyDog> CREATOR = new Parcelable.Creator<MyDog>() {

		@Override
		public MyDog createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			MyDog dog = new MyDog();
			dog.name = source.readString();
			dog.age = source.readInt();
			return dog;
		}

		@Override
		public MyDog[] newArray(int size) {
			// TODO Auto-generated method stub
			return new MyDog[size];
		}
	};

}
