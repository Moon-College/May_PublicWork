package com.dd.remote_aidl_server.aidl;
import com.dd.remote_aidl_server.aidl.MyOrder;
interface IUser{
	int getAge();
	void setAge(int age);
	MyOrder getMyOrder();
}