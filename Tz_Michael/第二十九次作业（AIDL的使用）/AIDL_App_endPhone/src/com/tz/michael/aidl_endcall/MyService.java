package com.tz.michael.aidl_endcall;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.android.internal.telephony.ITelephony;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class MyService extends Service {

	TelephonyManager tm;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		tm=(TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		tm.listen(new MyPhoneStatListenner(), PhoneStateListener.LISTEN_CALL_STATE);
	}
	
	private class MyPhoneStatListenner extends PhoneStateListener{

		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			super.onCallStateChanged(state, incomingNumber);
			switch (state) {
			case TelephonyManager.CALL_STATE_IDLE:
				//闲置
				
				break;
			case TelephonyManager.CALL_STATE_RINGING:
				//响铃
				if(incomingNumber.equals("10086")){
//					endCall();
					endCall2();
				}
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:
				//
				
				break;
			default:
				break;
			}
		}
		
	}

	/**
	 * 挂断电话
	 */
	public void endCall() {
		try {
			Class<?> clazz= Class.forName("android.os.ServiceManager");
			Method method=clazz.getMethod("getService", String.class);
			IBinder iBinder=(IBinder) method.invoke(null, Context.TELEPHONY_SERVICE);
			ITelephony iTelephony=ITelephony.Stub.asInterface(iBinder);
			iTelephony.endCall();
			Toast.makeText(MyService.this, "电话被拦截了", 0).show();
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
	/**
	 * my test
	 */
	public void endCall2(){
		try {
			Class<?> clazz = Class.forName("android.telephony.TelephonyManager");
			Method method_c=clazz.getDeclaredMethod("getDefault", (Class[]) null);
			Object object = method_c.invoke(null, (Object[])null);
			Method method=clazz.getDeclaredMethod("getITelephony", (Class[]) null );
			method.setAccessible(true);
			ITelephony iTelephony = (ITelephony) method.invoke(object, (Object[])null);
			iTelephony.endCall();
			Toast.makeText(MyService.this, "电话被拦截了", 0).show();
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
	
}
