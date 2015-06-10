package com.qfx.intentstartway.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {

	private String sname;
	
	private int sage;

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	

	public int getSage() {
		return sage;
	}

	public void setSage(int sage) {
		this.sage = sage;
	}
	
	

	@Override
	public String toString() {
		return "Student [sname=" + sname + ", sage=" + sage + "]";
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(sname);
		dest.writeInt(sage);
	}
	
	//此处必须命名为CREATOR，从parcelable中取数据
	public static final Parcelable.Creator<Student> CREATOR = new Creator<Student>() {
		//反序列化获取数组
		@Override
		public Student[] newArray(int size) {
			
			return new Student[size];
		}
		
		@Override
		public Student createFromParcel(Parcel source) {
			Student s = new Student();
			s.sname = source.readString();
			s.sage = source.readInt();
			return s;
		}
	};
	
	

}
