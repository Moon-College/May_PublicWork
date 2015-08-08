package com.tz.michael.aidl_server.bean;

import android.os.Parcel;
import android.os.Parcelable;
/**
 * 可以进程间传递的模板类
 * @author michael
 *
 */
public class ExampleBean implements Parcelable{

	private String name;
	private int age;
	private double weight;
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
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "ExampleBean [name=" + name + ", age=" + age + ", weight="
				+ weight + "]";
	}
	public ExampleBean() {
		super();
	}
	public int describeContents() {
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeInt(age);
		dest.writeDouble(weight);
	}
	
	public static final Parcelable.Creator<ExampleBean> CREATOR=new Creator<ExampleBean>() {
		
		public ExampleBean[] newArray(int size) {
			return new ExampleBean[size];
		}
		
		public ExampleBean createFromParcel(Parcel source) {
			ExampleBean exampleBean=new ExampleBean();
			exampleBean.setName(source.readString());
			exampleBean.setAge(source.readInt());
			exampleBean.setWeight(source.readDouble());
			return exampleBean;
		}
	};
}
