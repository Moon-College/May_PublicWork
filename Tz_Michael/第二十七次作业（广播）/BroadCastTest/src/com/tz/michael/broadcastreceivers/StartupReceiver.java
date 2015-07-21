package com.tz.michael.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
/**
 * 开机广播接受者
 * @author michael
 *
 */
public class StartupReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//这里做一些开机后要做的事
		Log.i("StartupReceiver--", "BOOT_COMPLETED,what you want do");
	}

}
