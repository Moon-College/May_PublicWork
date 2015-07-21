package com.decent.broadcast.receiver;

import com.decent.decentutil.DecentLogUtil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class FirstClassBroadcastReceiver extends BroadcastReceiver {

	private static final String TAG = "FirstClassBroadcastReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		DecentLogUtil.d(TAG, "FirstClass Received");
		Toast.makeText(context, "FirstClass Received", 50).show();
	}

}
