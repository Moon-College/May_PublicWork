package com.xigua.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SecondBroadcastReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i("INFO", "second received");
	}

}
