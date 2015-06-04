package com.decent.decentactivity.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable
{

	private String name;
	private int age;

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	
	@Override
	public int describeContents()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 写入接口，打包数据
	 * @see android.os.Parcelable#writeToParcel(android.os.Parcel, int)
	 */
	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		// TODO Auto-generated method stub
		dest.writeString(name);
		dest.writeInt(age);
	}

	public static final Parcelable.Creator<User> CREATOR
	= new Parcelable.Creator<User>(){

		@Override
		public User createFromParcel(Parcel source)
		{
			return new User(source);
		}

		@Override
		public User[] newArray(int size)
		{
			// TODO Auto-generated method stub
			return new User[size];
		}
		
	};
	
	public User(Parcel source)
	{
		name = source.readString();
		age = source.readInt();
		// TODO Auto-generated constructor stub
	}

	public User(String name,int age)
	{
		this.name = name;
		this.age = age;
		// TODO Auto-generated constructor stub
	}
}
