package com.ccgao.myactivity.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class MyClass implements Parcelable{
	private String className;
	private int count;
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int describeContents() {
		return 0;
	}
	//写入内存
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(className);
		dest.writeInt(count);
	}
	//从内存读出
	public final static Parcelable.Creator<MyClass> CREATOR =new Creator<MyClass>() {
		
		public MyClass[] newArray(int size) {
			return new MyClass[size];
		}
		
		public MyClass createFromParcel(Parcel source) {
			MyClass classes=new MyClass();
			classes.setClassName(source.readString());
			classes.setCount(source.readInt());
			return classes;
		}
	};
}
