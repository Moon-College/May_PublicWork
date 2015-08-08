package cn.ysh.aidl.server;

import cn.ysh.aidl.IUser;
import cn.ysh.aidl.MyOrder;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyService extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		return new MyBinder();
	}
	
	private class MyBinder extends IUser.Stub{

		@Override
		public int getAge() throws RemoteException {
			// TODO Auto-generated method stub
			return 20;
		}

		@Override
		public void setAge(int age) throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public MyOrder getMyOrder() throws RemoteException {
			MyOrder myOrder = new MyOrder();
			myOrder.setOrderNumber(00001);
			myOrder.setAddress("李家村万达广场");
			return myOrder;
		}
		
	}

}
