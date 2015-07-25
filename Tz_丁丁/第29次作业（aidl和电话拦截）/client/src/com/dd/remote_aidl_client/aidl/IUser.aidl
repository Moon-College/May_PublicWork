package com.dd.remote_aidl_client.aidl;
import com.dd.remote_aidl_client.aidl.MyOrder;
interface IUser{
	int getAge();
	void setAge(int age);
	MyOrder getMyOrder();
}