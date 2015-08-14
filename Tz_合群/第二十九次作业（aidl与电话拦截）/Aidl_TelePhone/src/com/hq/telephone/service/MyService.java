package com.hq.telephone.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.android.internal.telephony.ITelephony;
import com.hq.util.TelePhoneUtil;

public class MyService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		TelephonyManager tm = (TelephonyManager) this
				.getSystemService(Context.TELEPHONY_SERVICE);
		tm.listen(new TeleListener(), PhoneStateListener.LISTEN_CALL_STATE);
	}
	
	public class TeleListener extends PhoneStateListener {
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			switch (state) {
			case TelephonyManager.CALL_STATE_RINGING:
				//正在嘟嘟嘟中,挂电�?
				if(incomingNumber.equals("110")){
					try {
						endPhone(incomingNumber);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;
			case TelephonyManager.CALL_STATE_IDLE:
				//正常的时候，
				TelePhoneUtil.call("10000");
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:
				//挂电�?
				break;
			default:
				break;
			}
			super.onCallStateChanged(state, incomingNumber);
		}
		
		/**
		 * 挂电�?
		 * @param incomingNumber
		 * @throws Exception 
		 */
		private void endPhone(String incomingNumber) throws Exception {
			ITelephony telePhone = TelePhoneUtil.getTelePhone();
			if(telePhone != null){
				telePhone.endCall();
				Toast.makeText(MyService.this, "拦截成功!"+incomingNumber, 1000).show();
			}
		}
	}

}
