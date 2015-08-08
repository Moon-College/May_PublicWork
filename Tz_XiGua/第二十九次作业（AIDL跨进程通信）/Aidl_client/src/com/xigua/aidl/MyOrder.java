package com.xigua.aidl;

import android.os.Parcel;
import android.os.Parcelable;

public class MyOrder implements Parcelable{

	private String orderNo;
	private Float orderPrice;
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Float getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(Float orderPrice) {
		this.orderPrice = orderPrice;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(orderNo);
		dest.writeFloat(orderPrice);
	}
	
	public final static Parcelable.Creator<MyOrder> CREATOR = new Creator<MyOrder>() {
		
		@Override
		public MyOrder[] newArray(int size) {
			return new MyOrder[size];
		}
		
		@Override
		public MyOrder createFromParcel(Parcel source) {
			MyOrder order = new MyOrder();
			order.orderNo = source.readString();
			order.orderPrice = source.readFloat();
			return order;
		}
	};

}
