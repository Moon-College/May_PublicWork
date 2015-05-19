package com.binbin.utils;

import android.util.Log;

/**
 * 日志输出工具类
 * 
 * @author wangbin
 *
 */
public class LogUtils {

	private static final String TAG = LogUtils.class.getSimpleName();
	// 控制日志输出开关
	private static boolean logSwitch = false;


	
	public static boolean isLogSwitch() {
		return logSwitch;
	}


	public static void setLogSwitch(boolean logSwitch) {
		LogUtils.logSwitch = logSwitch;
	}


	public static int printLog(int type, String tag, String info) {
		if(null==tag || ("").equals(tag)){
			tag=TAG;
		}
		if (logSwitch) {
			return Log.println(type, tag, info);
		}
		return 0;
	}

}
