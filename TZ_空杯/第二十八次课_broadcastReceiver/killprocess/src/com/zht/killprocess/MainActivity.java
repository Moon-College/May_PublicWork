/**
 * Project Name:killprocess
 * File Name:MainActivity.java
 * Package Name:com.zht.killprocess
 * Date:2015-7-27下午10:52:10
 * Copyright (c) 2015, hongtao8@staff.sina.com.cn All Rights Reserved.
 *
 */

package com.zht.killprocess;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

/**
 * ClassName:MainActivity <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-7-27 下午10:52:10 <br/>
 * 
 * @author hongtao
 * @version
 * @since JDK 1.6
 * @see
 */
public class MainActivity extends Activity {
	private ScreenBroadcastReceiver mScreenReceiver;
	private List<TaskInfo> userTask = new ArrayList<TaskInfo>();
	private TaskUtils utils;
	private int progressCount;
	private long availMemory;
	long all_memory = 0;
	private List<TaskInfo> allTask;

	class ScreenBroadcastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (Intent.ACTION_SCREEN_ON.equals(action)) {
				// 开屏
			} else if (Intent.ACTION_SCREEN_OFF.equals(action)) {
				Log.i("INFO", "screen off");
				// 锁屏 杀掉后台所有进程
				killProcess();
			} else if (Intent.ACTION_USER_PRESENT.equals(action)) {
				// 解锁
			}
		}
	}

	private void killProcess() {
		ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		List<TaskInfo> killTask = new ArrayList<TaskInfo>();
		for (TaskInfo info : userTask) {
			// 是否是自身软件
			if (!info.getPackageName().equals(this.getPackageName())) {
				am.killBackgroundProcesses(info.getPackageName());
				killTask.add(info);
			}
		}
		// 把userTask、systemTask里面的任务移除
		long memory = 0;
		for (TaskInfo info : killTask) {
			memory += info.getMemory();
		}

		// 提示（杀了多少进程、节省了多少内存）
		int count = killTask.size();
		Log.i("INFO","杀死了" + count + "个进程，释放了"+ TextFormat.formatByte(memory * 1024) + "内存");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mScreenReceiver = new ScreenBroadcastReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		filter.addAction(Intent.ACTION_USER_PRESENT);
		this.registerReceiver(mScreenReceiver, filter);

		initData();
	}

	private void initData() {
		utils = new TaskUtils(this);
		// 开启线程，计算进程数和所占内存
		new Thread(new Runnable() {

			@Override
			public void run() {
				// 通过TaskUtil的方法加载相应的数据，并并装入对应的集合
				progressCount = utils.getProgressCount();
				availMemory = utils.getAvailMemory();
				allTask = utils.getProgress();
				// 将进程分为 ：用户进程+系统进程
				for (TaskInfo info : allTask) {
					// 计算所有程序的总和
					all_memory += info.getMemory();
					if (info.isUserTask()) {
						userTask.add(info);
					} 
				}
			}
		}).start();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.unregisterReceiver(mScreenReceiver);
	}
}
