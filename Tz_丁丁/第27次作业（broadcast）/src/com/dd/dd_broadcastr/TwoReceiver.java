package com.dd.dd_broadcastr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TwoReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		this.abortBroadcast();
		Log.v("home", "two×ß");
	}

}
