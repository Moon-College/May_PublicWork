package com.junwen.broadcastreceiver.reveiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.junwen.broadcastreceiver.activity.MainActivity;

public class BootBroadcastReveiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		//开机启动
		Intent start = new Intent();
		start.setClass(context, MainActivity.class);
		start.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(start);
	}

}
