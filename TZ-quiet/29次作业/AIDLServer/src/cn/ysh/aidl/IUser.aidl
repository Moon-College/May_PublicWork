package cn.ysh.aidl;
import cn.ysh.aidl.MyOrder;
interface IUser{
	int getAge();
	void setAge(int age);
	MyOrder getMyOrder();
}