package com.xigua.aidl;
import com.xigua.aidl.MyOrder;
interface IUser{
   String getName();
   void setName(String name);
   MyOrder getMyOrder(); 
}