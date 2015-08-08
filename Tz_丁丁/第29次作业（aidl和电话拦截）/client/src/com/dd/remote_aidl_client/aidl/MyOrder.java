package com.dd.remote_aidl_client.aidl;

import android.os.Parcel;
import android.os.Parcelable;

public class MyOrder implements Parcelable{
	private String orderName;
	private float orderPrice;
	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public float getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(float orderPrice) {
		this.orderPrice = orderPrice;
	}

	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(orderName);
		dest.writeFloat(orderPrice);
	}

	public final static Parcelable.Creator<MyOrder> CREATOR = new Parcelable.Creator<MyOrder>() {

		public MyOrder createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			MyOrder order = new MyOrder();
			order.orderName = source.readString();
			order.orderPrice = source.readFloat();
			return order;
		}

		public MyOrder[] newArray(int size) {
			// TODO Auto-generated method stub
			return new MyOrder[size];
		}
		
	};
}
