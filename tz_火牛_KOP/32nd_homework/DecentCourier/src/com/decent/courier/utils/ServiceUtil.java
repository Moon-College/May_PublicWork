package com.decent.courier.utils;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;

public class ServiceUtil {
	/**
	 * ��ѯ��Ӧ�ķ����Ƿ�������
	 * 
	 * @param context
	 *            ������
	 * @param serviceName
	 *            �����������
	 * @return �Ƿ�������
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
