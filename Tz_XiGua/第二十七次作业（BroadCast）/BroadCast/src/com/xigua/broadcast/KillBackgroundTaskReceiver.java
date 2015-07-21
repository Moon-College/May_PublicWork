package com.xigua.broadcast;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class KillBackgroundTaskReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
        	Log.i("INFO", "Received_screen_off");
        	//获取ActivityManager对象
        	ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        	//获取系统中所有正在运行的进程
        	List<RunningAppProcessInfo> appInfos = am.getRunningAppProcesses();
        	Log.i("INFO", "size:"+appInfos.size());
        	String currentProcessName = context.getApplicationContext().getPackageName();
        	Log.i("INFO", "cname:"+currentProcessName);
        	//kill所有除本进程以外的所有进程
        	for(RunningAppProcessInfo appinfo : appInfos){
        		String processName = appinfo.processName;
        		if(!processName.equals(currentProcessName)){
        			Log.i("INFO", "othername:"+processName);
        			am.killBackgroundProcesses(processName);
        		}
        	}
        }
	}

}
