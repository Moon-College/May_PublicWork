package com.dd.remote_aidl_client;

import com.dd.remote_aidl_client.aidl.IUser;
import com.dd.remote_aidl_client.aidl.MyOrder;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	MyServiceConnection conn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		conn = new MyServiceConnection();
	}
	public void bind(View v){
    	Intent intent = new Intent();
    	intent.setAction("com.tz.aidl");
    	this.bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }
	
	private class MyServiceConnection implements ServiceConnection{

		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			Log.i("INFO", "远程服务绑定成功");
			IUser asInterface = IUser.Stub.asInterface(service);
			try {
				int age = asInterface.getAge();
				Log.i("INFO", "age:"+age);
				MyOrder myOrder = asInterface.getMyOrder();
				Log.i("INFO", myOrder.getOrderName());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
    	
    }
	
	 @Override
	    protected void onDestroy() {
	    	// TODO Auto-generated method stub
	    	super.onDestroy();
	    	if(conn !=null){
	    		this.unbindService(conn);
	    	}
	    }
}
