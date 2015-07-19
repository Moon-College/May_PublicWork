package com.junwen.broadcastreceiver.reveiver;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class LockScreenBroadcastReveiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		//取得Activity管理器
		ActivityManager am =(ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		//取得所有正在运行的APP信息
		List<RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();
		for (RunningAppProcessInfo info : runningAppProcesses) {
			//遍历出每一个包名
					if(!info.processName.equals("com.example.broadcastreceiver")){
						//杀死进程
						am.killBackgroundProcesses(info.processName);
				}
		}
	}
}
