package com.junwen.aidl.activity;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.junwen.aidl.IService;
import com.junwen.aidl.Student;

public class MyService extends Service{
	
	private Mybinder binder;
	@Override
	public IBinder onBind(Intent intent) {
		binder = new Mybinder();
		return binder;
	}
	public class Mybinder extends IService.Stub{

		@Override
		public String getName() throws RemoteException {
			return "卜俊文";
		}

		@Override
		public int getAge() throws RemoteException {
			return 18;
		}

		@Override
		public String getSex() throws RemoteException {
			return "男";
		}

		@Override
		public Student getStudent() throws RemoteException {
			Student stu = new Student();
			stu.setAge(20);
			stu.setName("卜俊文");
			stu.setSex("男");
			return stu;
		}
		
	}
}
