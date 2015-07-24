package com.tz.michael.aidl_install;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Service;
import android.content.Intent;
import android.content.pm.IPackageInstallObserver;
import android.content.pm.IPackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.os.RemoteException;

public class InstallService extends Service {

	File f;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		f=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/IOC_Test.apk");
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		new Thread(new Runnable() {
			
			public void run() {
				installApp(f);
			}
		}).start();
	}

	private void installApp(File f) {
		Class<?> clazz;
		try {
			clazz = Class.forName("android.os.ServiceManager");
			Method method=clazz.getMethod("getService", String.class);
			IBinder iBinder=(IBinder) method.invoke(null, "package");
			IPackageManager iPackageManager=IPackageManager.Stub.asInterface(iBinder);
			iPackageManager.installPackage(Uri.fromFile(f), new MyInstallObserver(), 0x00000002, f.getPath());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	private class MyInstallObserver extends IPackageInstallObserver.Stub{

		public void packageInstalled(String packageName, int returnCode)
				throws RemoteException {
			notifyAll();
		}
		
	}
	
}
