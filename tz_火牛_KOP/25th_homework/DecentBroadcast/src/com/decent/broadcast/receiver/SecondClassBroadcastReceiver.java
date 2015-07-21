package com.decent.broadcast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.decent.decentutil.DecentLogUtil;

public class SecondClassBroadcastReceiver extends BroadcastReceiver {

	private static final String TAG = "SecondClassBroadcastReceiver";
	private static boolean readyToAbort = false;

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		DecentLogUtil.d(TAG, "SecondClass Received");
		Toast.makeText(context, "SecondClass Received", 50).show();
		if (readyToAbort) {
			this.abortBroadcast();
			readyToAbort = false;
			DecentLogUtil.d(TAG, "SecondClass abort this broadcast");
			Toast.makeText(context, "SecondClass abort this broadcast", 50)
					.show();
		}
	}

	public static boolean isReadyToAbort() {
		return readyToAbort;
	}

	public static void setReadyToAbort(boolean isAbort) {
		readyToAbort = isAbort;
	}

}
