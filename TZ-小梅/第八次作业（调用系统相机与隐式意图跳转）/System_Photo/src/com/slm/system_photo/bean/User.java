package com.slm.system_photo.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
	private String name;
	private String sex;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
	@Override
	public String toString() {
		return "User [name=" + name + ", sex=" + sex + "]";
	}

	public static Parcelable.Creator<User> getCreator() {
		return CREATOR;
	}

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel out, int flags) {
		out.writeString(name);
		out.writeString(sex);
	}

	public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
		//反序列化获取数组的
		public User createFromParcel(Parcel in) {
			User user = new User();
			user.name = in.readString();
			user.sex = in.readString();
			return user;
		}

		public User[] newArray(int size) {
			return new User[size];
		}
	};

}
