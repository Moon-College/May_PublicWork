/**
 * Created on 2015年8月14日下午7:56:22 package-info.java<br/>
 * Author ZhuHequn 1363790125@qq.com <br/>
 * version 1.0 TODO
 */
package com.hq.service.server;

import com.hq.service.aidl.IDog;
import com.hq.service.aidl.MyDog;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyService extends Service {
	private MyBinder binder;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		binder = new MyBinder();// 初始化
		return binder;
	}

	private class MyBinder extends IDog.Stub {

		@Override
		public MyDog getMyDog() throws RemoteException {
			// TODO Auto-generated method stub
			MyDog mydog = new MyDog();
			mydog.setName("旺财");
			mydog.setAge(4);

			return mydog;
		}

	}

}