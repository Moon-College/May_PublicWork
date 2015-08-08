package com.tz.michael.aidl_server;

import com.tz.michael.aidl_server.bean.ExampleBean;
import com.tz.michael.aidl_server.bean.IExample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
/**
 * 充当服务端的app中的service
 * @author michael
 *
 */
public class ServerService extends Service {

	private String testContent;
	private ExampleBean eBean;
	
	@Override
	public IBinder onBind(Intent intent) {
		MyBinder binder=new MyBinder();
		return binder;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		testContent="make a pratice";
		eBean=new ExampleBean();
		eBean.setName("michael");
		eBean.setAge(24);
		eBean.setWeight(140.0);
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
	}
	
	private class MyBinder extends IExample.Stub{

		public String getStringFromServer() throws RemoteException {
			return testContent;
		}

		public ExampleBean getExampleBean() throws RemoteException {
			return eBean;
		}
		
	}
	
}
