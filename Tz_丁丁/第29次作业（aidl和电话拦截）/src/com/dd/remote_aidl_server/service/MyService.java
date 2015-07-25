package com.dd.remote_aidl_server.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.dd.remote_aidl_server.aidl.MyOrder;
import com.dd.remote_aidl_server.aidl.IUser;

public class MyService extends Service {
private MyBinder binder ;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		binder = new MyBinder();//初始化
		return binder;
	}

	
	private class MyBinder extends IUser.Stub{

		public int getAge() throws RemoteException {
			// TODO Auto-generated method stub
			return 18;
		}

		public void setAge(int age) throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		public MyOrder getMyOrder() throws RemoteException {
			// TODO Auto-generated method stub
			MyOrder myOrder = new MyOrder();
			myOrder.setOrderName("好一份订单");
			myOrder.setOrderPrice(100f);
			return myOrder;
		}
		
	}
}
