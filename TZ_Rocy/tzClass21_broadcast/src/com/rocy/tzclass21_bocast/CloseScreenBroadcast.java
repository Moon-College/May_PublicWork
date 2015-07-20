package com.rocy.tzclass21_bocast;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import android.widget.Toast;

public class CloseScreenBroadcast extends BroadcastReceiver {

	private ActivityManager am;
	private PackageManager pm;
	private PackageInfo packageInfo;

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Toast.makeText(context, "锁屏了", 0).show();
		am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		pm = (PackageManager)context.getPackageManager();
		try {
			packageInfo = pm.getPackageInfo(context.getPackageName(),PackageManager.GET_SIGNATURES);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<RunningAppProcessInfo> processes = am.getRunningAppProcesses();
		for (RunningAppProcessInfo runningAppProcessInfo : processes) {
			if(runningAppProcessInfo.processName.equals(packageInfo.packageName)){
				//不杀死自己
				Log.i("INFO", "Rocy_name:"+runningAppProcessInfo.processName);
			   continue;
			}
			am.killBackgroundProcesses(runningAppProcessInfo.processName);
		}
		
	}

}
