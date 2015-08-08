package com.xigua.aidl_endcall;


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

public class MyEndCallService extends Service {

	private TelephonyManager tm;
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		//获取系统管理电话的服务
		tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		//监听电话的状态
		tm.listen(new MyPhoneStateListener(), PhoneStateListener.LISTEN_CALL_STATE);
		return super.onStartCommand(intent, flags, startId);
	}

	private class MyPhoneStateListener extends PhoneStateListener{
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			switch (state) {
			case TelephonyManager.CALL_STATE_IDLE:
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:
				break;
				//响铃状态
			case TelephonyManager.CALL_STATE_RINGING:
				if(incomingNumber.equals("110")){
					try {
						endCall(incomingNumber);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;
			default:
				break;
			}
			super.onCallStateChanged(state, incomingNumber);
		}

		private void endCall(String incomingNumber) {
   			
   			try {
   				Class<?> serverManager = Class.forName("android.os.ServiceManager");
				Method method = serverManager.getMethod("getService", String.class);
				IBinder ibinder = (IBinder) method.invoke(null, Context.TELEPHONY_SERVICE);
				ITelephony itelephony = ITelephony.Stub.asInterface(ibinder);
				itelephony.endCall();
				Toast.makeText(MyEndCallService.this, "成功拦截电话："+incomingNumber, 1000).show();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
}
