package com.ccg.log.utils;

import android.util.Log;

/**
 * 通用日志打印
 * @author ccgao
 *
 */
public class MyLog {

	// 日志的开关
	public static boolean isDebug;

	/**
	 * verbose日志输出
	 * 
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int v(String tag, String msg) {
		if (isDebug) {
			// verbose日志可以正常输出
			return Log.v(tag, msg);
		}
		return 0;
	}

	/**
	 * debug日志输出
	 * 
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int d(String tag, String msg) {
		if (isDebug) {
			// debug日志可以正常输出
			return Log.d(tag, msg);
		}
		return 0;
	}

	/**
	 * Info日志输出
	 * 
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int i(String tag, String msg) {
		if (isDebug) {
			// info日志可以正常输出
			return Log.i(tag, msg);
		}
		return 0;
	}

	/**
	 * warn日志输出
	 * 
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int w(String tag, String msg) {
		if (isDebug) {
			// warn日志可以正常输出
			return Log.i(tag, msg);
		}
		return 0;
	}

	/**
	 * error日志输出
	 * 
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int e(String tag, String msg) {
		if (isDebug) {
			// error日志可以正常输出
			return Log.i(tag, msg);
		}
		return 0;
	}

	/**
	 * assert日志输出
	 * 
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int a(String tag, String msg) {
		if (isDebug) {
			// assert日志可以正常输出
			return Log.i(tag, msg);
		}
		return 0;
	}

	/**
	 * 按条件输出相应日志
	 * @param priority
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int p(int priority, String tag, String msg) {
		if (isDebug) {
			// println传参控制日志输出类型，日志正常输出
			return Log.println(priority, tag, msg);
		}
		return 0;
	}
}
