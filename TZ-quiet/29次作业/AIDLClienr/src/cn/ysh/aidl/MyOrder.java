package cn.ysh.aidl;

import android.os.Parcel;
import android.os.Parcelable;

public class MyOrder implements Parcelable{
	private int orderNumber;
	private String address;
	
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(orderNumber);
		dest.writeString(address);
		
	}
	public final static Creator<MyOrder> CREATOR = new Creator<MyOrder>() {

		@Override
		public MyOrder createFromParcel(Parcel source) {
			MyOrder myOrder = new MyOrder();
			myOrder.orderNumber = source.readInt();
			myOrder.address = source.readString();
			return myOrder;
		}

		@Override
		public MyOrder[] newArray(int size) {
			// TODO Auto-generated method stub
			return new MyOrder[size];
		}
	};
}
