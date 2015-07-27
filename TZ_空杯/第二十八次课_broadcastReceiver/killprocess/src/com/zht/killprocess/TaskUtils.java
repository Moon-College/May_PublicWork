/**
 * Project Name:killprocess
 * File Name:TaskUtils.java
 * Package Name:com.zht.killprocess
 * Date:2015-7-27下午11:21:46
 * Copyright (c) 2015, hongtao8@staff.sina.com.cn All Rights Reserved.
 *
 */

package com.zht.killprocess;

import java.util.ArrayList;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;

/**
 * ClassName:TaskUtils <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-7-27 下午11:21:46 <br/>
 * 
 * @author hongtao
 * @version
 * @since JDK 1.6
 * @see
 */
public class TaskUtils {
	private Context context;
	private ActivityManager am;

	public TaskUtils(Context context) {
		super();
		this.context = context;
		am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
	}

	// ActivityManager和PackageManager和ApplicationInfo
	// 进程数
	public int getProgressCount() {
		List<RunningAppProcessInfo> runningAppProcesses = am
				.getRunningAppProcesses();
		return runningAppProcesses.size();
	}

	// 可用内存Byte (总内存=可用类存+所有进程占用内存的和)
	public long getAvailMemory() {
		MemoryInfo outInfo = new MemoryInfo();
		am.getMemoryInfo(outInfo);
		return outInfo.availMem;

	}

	// 当前正在运行的进程（图标、名字、占用内存KB、是否是用户进程、包名、isUserTask）
	public List<TaskInfo> getProgress() {
		List<TaskInfo> list = new ArrayList<TaskInfo>();
		List<RunningAppProcessInfo> runningAppProcesses = am
				.getRunningAppProcesses();
		PackageManager pm = context.getPackageManager();
		for (RunningAppProcessInfo info : runningAppProcesses) {
			TaskInfo task = new TaskInfo();
			// 进程id
			int pid = info.pid;
			// 包名
			String packageName = info.processName;
			try {
				// 占用内存，图标，名字
				int[] pids = new int[] { pid };
				android.os.Debug.MemoryInfo[] processMemoryInfo = am
						.getProcessMemoryInfo(pids);
				// 每一个进程占用内存 单位kb
				int memory = processMemoryInfo[0].getTotalPrivateDirty();
				ApplicationInfo applicationInfo = pm.getApplicationInfo(
						packageName, PackageManager.GET_ACTIVITIES);
				// 图标
				Drawable icon = applicationInfo.loadIcon(pm);
				// 名字
				String name = applicationInfo.loadLabel(pm).toString();
				// 为对象赋值
				task.setPid(pid);
				task.setPackageName(packageName);
				task.setMemory(memory);
				task.setIcon(icon);
				task.setName(name);
				// 是否是用户进程
				task.setUserTask(filterApp(applicationInfo));
			} catch (NameNotFoundException e) {
				// 如果抛异常，则设置默认参数
				task.setIcon(context.getResources().getDrawable(
						R.drawable.ic_launcher));
				task.setName(packageName);
			}
			list.add(task);
		}
		return list;

	}

	// 判断应用程序是否是用户程序
	public boolean filterApp(ApplicationInfo info) {
		// 原来是系统应用，用户手动升级
		if ((info.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
			return true;
			// 用户自己安装的应用程序
		} else if ((info.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
			return true;
		}
		return false;
	}
}
