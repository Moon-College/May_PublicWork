package com.decent.aidlserver.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Info implements Parcelable {

	private int id;
	private String message;
	public static final Parcelable.Creator<Info> CREATOR = new Creator<Info>() {

		@Override
		public Info createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new Info(source);
		}

		@Override
		public Info[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Info[size];
		}
	};
	
	public Info(Parcel source) {
		// TODO Auto-generated constructor stub
		id = source.readInt();
		message = source.readString();
	}

	public Info(int id,String message) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.message = message;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeInt(id);
		dest.writeString(message);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
