package com.decent.courier.utils;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;

public class ServiceUtil {
	/**
	 * 查询对应的服务是否在运行
	 * 
	 * @param context
	 *            上下文
	 * @param serviceName
	 *            服务的类名称
	 * @return 是否在运行
	 */
	public static boolean isServiceRunning(Context context, String serviceName) {
		boolean result = false;
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningServiceInfo> runningList = am.getRunningServices(100);
		for (RunningServiceInfo serviceInfo : runningList) {
			String runningServiceName = serviceInfo.service.getClassName();
			//DecentLogUtil.d(runningServiceName+"is running!!!");
			if (runningServiceName.equals(serviceName)) {
				DecentLogUtil.d(serviceName+" is running!!!");
				result = true;
			}
		}
		return result;
	}
}
