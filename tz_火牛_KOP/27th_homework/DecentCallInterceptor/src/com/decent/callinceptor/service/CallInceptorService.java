package com.decent.callinceptor.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.decent.callinceptor.util.CallInterceptorUtil;

public class CallInceptorService extends Service {

	private TelephonyManager mTelephonyManager;
	private PhoneCallListener mPhoneCallListener;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		Log.d("INFO", "into onCreate");
		// TODO Auto-generated method stub
		if (mTelephonyManager == null) {
			// »ñµÃtelephoneµÄservice
			mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
			// ×¢²á¼àÌýListener,¼àÌýcall_state-----LISTEN_CALL_STATE
			mPhoneCallListener = new PhoneCallListener();
			mTelephonyManager.listen(mPhoneCallListener,
					PhoneStateListener.LISTEN_CALL_STATE);
		}
		super.onCreate();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		return super.onUnbind(intent);
	}

	public class PhoneCallListener extends PhoneStateListener {

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
				Log.d("INFO", "into call ringing state!!!");
				if (incomingNumber.endsWith("5554")) {
					boolean result = CallInterceptorUtil.endCall(incomingNumber);
					Log.d("INFO", "call endCall result = "+result);
					CallInterceptorUtil.call(incomingNumber);
					Log.d("INFO", "begin to call incomingNumber = "+incomingNumber);
				}
				break;
			default:
				break;
			}
		}

	}
}
