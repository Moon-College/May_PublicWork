package com.tz.asy.common;

import java.util.List;


import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import com.tz.asy.utils.MyLog;

public class ServiceConnection {
	public static boolean isServiceRunning(Context context,String serviceName){
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningServiceInfo> runningServices = am.getRunningServices(100);
		for(RunningServiceInfo info : runningServices){
			String name = info.service.getClassName();
			if(name.equals(serviceName)){
				MyLog.d(serviceName + " is Running");
				return true;
			}
		}
		return false;
	}
}
