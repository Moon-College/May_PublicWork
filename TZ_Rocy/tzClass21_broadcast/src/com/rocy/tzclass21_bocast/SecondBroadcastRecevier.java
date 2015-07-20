package com.rocy.tzclass21_bocast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SecondBroadcastRecevier extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Toast.makeText(context, "这是我的第二次哦", 0).show();
		this.abortBroadcast();
	}

}
