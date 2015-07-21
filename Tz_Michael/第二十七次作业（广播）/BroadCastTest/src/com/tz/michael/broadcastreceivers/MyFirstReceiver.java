package com.tz.michael.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyFirstReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		int index=intent.getIntExtra("index", 0);
		Log.i("MyFirstReceiver--", index+"");
	}

}
