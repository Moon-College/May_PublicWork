package com.tz.camera;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable{

	private String name;	
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	/*下面实现的是对象的序列化*/
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(address);
	}
	
	public static final Parcelable.Creator<User> CREATOP = new Creator<User>() {

		@Override
		public User createFromParcel(Parcel source) {
			User user = new User();
			user.name = source.readString();
			user.address = source.readString();
			return user;
		}

		@Override
		public User[] newArray(int size) {			
			return new User[size];
		}
	};

}
