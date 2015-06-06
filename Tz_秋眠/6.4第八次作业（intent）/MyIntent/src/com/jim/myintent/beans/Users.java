package com.jim.myintent.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class Users implements Parcelable {
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


	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(name);
		dest.writeInt(age);
	}

	public static final Parcelable.Creator<Users> CREATOR = new Creator<Users>() {

		public Users[] newArray(int size) {
			// TODO Auto-generated method stub

			return new Users[size];
		}

		public Users createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			Users users = new Users();
			users.setName(source.readString());
			users.setAge(source.readInt());
			return users;
		}
	};

}
