package com.casit.hc;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

public class MyClass implements Parcelable{

    public String className ;
    public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getStudentsCount() {
		return studentsCount;
	}
	public void setStudentsCount(int studentsCount) {
		this.studentsCount = studentsCount;
	}
	public int studentsCount;
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(className);
		dest.writeInt(studentsCount);
	}
	public final static Parcelable.Creator<MyClass> CREATOR = new Creator<MyClass>() {
		
		public MyClass[] newArray(int size) {
			// TODO Auto-generated method stub			
			return new MyClass[size];
		}
		
		public MyClass createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			MyClass myc = new MyClass();
			myc.className = source.readString();
			myc.studentsCount = source.readInt();
			return myc;
		}
	};
	
}
