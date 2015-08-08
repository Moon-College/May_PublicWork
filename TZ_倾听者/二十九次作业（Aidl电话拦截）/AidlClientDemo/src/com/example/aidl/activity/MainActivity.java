package com.example.aidl.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

import com.example.aidlclientdemo.R;
import com.junwen.aidl.Student;

public class MainActivity extends Activity {
	
	private ServiceConnect conn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		conn = new ServiceConnect();
	}
	/**
	 * 点击
	 * @param view
	 */
	public void onclick(View view){
		//远程绑定服务
		Intent intent = new Intent();
		intent.setAction("com.junwen.aidlaction");
		this.bindService(intent, conn, BIND_AUTO_CREATE);
	}
	public class ServiceConnect implements ServiceConnection{

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			com.junwen.aidl.IService mService = com.junwen.aidl.IService.Stub.asInterface(service);
			try {
				System.out.println(mService.getAge()+"年龄");
				Student student = mService.getStudent();
				System.out.println("名字"+student.getName());
				System.out.println("年龄"+student.getAge());
				System.out.println("姓名"+mService.getName());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		
			
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			
		}
		
	}
}
