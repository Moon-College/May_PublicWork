package com.xigua.aidl_server.service;

import com.xigua.aidl.IUser;
import com.xigua.aidl.MyOrder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class Myservice extends Service {

	private MyBinder myBinder;
	@Override
	public IBinder onBind(Intent intent) {
		myBinder = new MyBinder();
		return myBinder;
	}

	class MyBinder extends IUser.Stub{

		@Override
		public String getName() throws RemoteException {
			return "XiGua";
		}

		@Override
		public void setName(String name) throws RemoteException {
			
		}

		@Override
		public MyOrder getMyOrder() throws RemoteException {
			MyOrder order = new MyOrder();
			order.setOrderNo("No:90016");
			order.setOrderPrice(100f);
			return order;
		}
		
	}
}
