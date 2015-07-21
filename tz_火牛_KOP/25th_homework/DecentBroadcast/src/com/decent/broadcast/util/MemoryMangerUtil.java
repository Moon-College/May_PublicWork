package com.decent.broadcast.util;

import java.util.List;

import com.decent.decentutil.DecentLogUtil;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;

public class MemoryMangerUtil {

	private static final String TAG = "MemoryMangerUtil";

	/**
	 * 杀掉所有后台运行的进程
	 * 
	 * @param context
	 *            上下文
	 * @param levelRestrict kill掉大于xxxxlevel的进程,如果传<0的数字，则使用默认——杀掉大于RunningAppProcessInfo.IMPORTANCE_VISIBLE的
	 *              
	 */
	public static void killAllRunningProcess(Context context,int levelRestrict) {
		//如果levelRestrict小于0则，使用默认IMPORTANCE_VISIBLE
		int level = levelRestrict<0?RunningAppProcessInfo.IMPORTANCE_VISIBLE:levelRestrict;
		// 1、获取activityManager
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		// 2、通过am获取当前正在运行的进程
		List<RunningAppProcessInfo> allRunningProcList = am
				.getRunningAppProcesses();

		// 3、遍历allRunningProcList，kill
		for (RunningAppProcessInfo rapInfo : allRunningProcList) {
			if (rapInfo.importance > level) {
				// 3.1、 获取这个process相关的package名字
				String[] relativePaks = rapInfo.pkgList;
				// 3.2、使用killBackgroundProcesses去kill对应的package
				// kill需要权限
				// android.Manifest.permission.KILL_BACKGROUND_PROCESSES
				for (String pakName : relativePaks) {
					am.killBackgroundProcesses(pakName);
					DecentLogUtil.d(TAG, "package:" + pakName
							+ " has been killed");
				}
			}
		}
	}

	/**
	 * 获得当前系统剩余的内存数单位是byte
	 * 
	 * @param context
	 *            上下文
	 * @return 当前系统剩余的内存数单位是byte
	 */
	public static long getAvailMemorySize(Context context) {
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		MemoryInfo mi = new MemoryInfo();
		am.getMemoryInfo(mi);
		return mi.availMem;
	}
}
