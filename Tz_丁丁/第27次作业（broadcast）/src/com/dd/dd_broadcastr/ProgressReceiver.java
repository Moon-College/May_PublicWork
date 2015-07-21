package com.dd.dd_broadcastr;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ProgressReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.v("home", "ProgressReceiver×ß");
		ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> runningAppProcesses = manager.getRunningAppProcesses();
		for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
			Package package1 = runningAppProcessInfo.getClass().getPackage();
			String name = package1.getName();
			Log.v("home", name+";;;");
		}
//		manager.killBackgroundProcesses(packageName);
	}

}
