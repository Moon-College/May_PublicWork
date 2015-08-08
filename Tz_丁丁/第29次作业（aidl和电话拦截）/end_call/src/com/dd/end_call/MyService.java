package com.dd.end_call;

import java.lang.reflect.Method;

import com.android.internal.telephony.ITelephony;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service{
	private TelephonyManager tm;//管理电话的类
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		tm.listen(new MyPhoneStateListener(), PhoneStateListener.LISTEN_CALL_STATE);
	}
	
	
	private class MyPhoneStateListener extends PhoneStateListener{
			@Override
			public void onCallStateChanged(int state, String incomingNumber) {
				// TODO Auto-generated method stub
				super.onCallStateChanged(state, incomingNumber);
				switch (state) {
				case TelephonyManager.CALL_STATE_IDLE:
					
					break;
				case TelephonyManager.CALL_STATE_OFFHOOK:
					break;
				case TelephonyManager.CALL_STATE_RINGING:
					//监听电话叫起的状态
					Log.i("INFO", "phone ringing");
					//检测电话号码，如果是110打过来的，就挂掉
					if(incomingNumber.equals("110")){
						try {
							endCall(incomingNumber);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					break;
				default:
					break;
				}
			}

			private void endCall(String incomingNumber) throws Exception {
				//反射
				Class<?> forName = Class.forName("android.os.ServiceManager");
				Method method = forName.getMethod("getService", String.class);
				IBinder ibinder = (IBinder) method.invoke(null, Context.TELEPHONY_SERVICE);
				ITelephony iTelephony = ITelephony.Stub.asInterface(ibinder);
				iTelephony.endCall();//挂掉电话
				Toast.makeText(MyService.this, "成功拦截电话："+incomingNumber, 1000).show();
				//
			}
	}
}