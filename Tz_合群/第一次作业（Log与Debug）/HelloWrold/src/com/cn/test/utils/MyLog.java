package com.cn.test.utils;

import android.util.Log;

/**
 * Created on2015-5-18 下午6:48:54 MyLog.java<br/>
 * Author ZhuHequn 1363790125@qq.com <br/>
 * version 1.0 TODO
 */
public class MyLog {

	public static boolean isDebug; // Log日志开关

	public static int println(int priority, String tag, String msg) {
		if (isDebug) {
			// 当isDebug为true时执行Log日志
			return Log.println(priority, tag, msg);
		}
		return 0;
	}

	// VERBOSE 日志 方法
	public static int v(String tag, String msg) {
		if (isDebug) {
			// 当isDebug为true时执行Log日志
			return Log.v(tag, msg);
		}
		return 0;
	}

	// DEBUG 日志方法
	public static int d(String tag, String msg) {
		if (isDebug) {
			// 当isDebug为true时执行Log日志
			return Log.d(tag, msg);
		}
		return 0;
	}

	// INFO 日志方法
	public static int i(String tag, String msg) {
		if (isDebug) {
			// 当isDebug为true时执行Log日志
			return Log.i(tag, msg);
		}
		return 0;
	}

	// WARN 日志方法
	public static int w(String tag, String msg) {
		if (isDebug) {
			// 当isDebug为true时执行Log日志
			return Log.w(tag, msg);
		}
		return 0;
	}

	// ERROR 日志方法
	public static int e(String tag, String msg) {
		if (isDebug) {
			// 当isDebug为true时执行Log日志
			return Log.e(tag, msg);
		}
		return 0;
	}

	// ASSERT 日志方法
	public static int a(String tag, String msg) {
		if (isDebug) {
			// 当isDebug为true时执行Log日志
			return Log.ASSERT;
		}
		return 0;
	}
}
