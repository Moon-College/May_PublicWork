package com.tz.log.utils;

import android.util.Log;

public class MyLog {
	public static boolean isdebug; // 日志开关

	/**
	 * verbose 表示打印日志的优先级 tag：过滤条件 msg：日志内容
	 * 
	 * @return int
	 */
	public static int v(String tag, String msg) {
		if (isdebug) {
			return Log.v(tag, msg);
		}
		return 0;
	}

	/**
	 * debug 表示打印日志的优先级 tag：过滤条件 msg：日志内容
	 * 
	 * @return int
	 */
	public static int d(String tag, String msg) {
		if (isdebug) {
			return Log.d(tag, msg);
		}
		return 0;
	}

	/**
	 * info 表示打印日志的优先级
	 * 
	 * @param tag
	 *            :过滤条件
	 * @param msg
	 *            :日志 内容
	 * @return int
	 */
	public static int i(String tag, String msg) {
		if (isdebug) {
			return Log.i(tag, msg);
		}
		return 0;
	}

	/**
	 * warn 表示打印日志的优先级
	 * 
	 * @param tag
	 *            :过滤条件
	 * @param msg
	 *            :日志内容
	 * @return int
	 */
	public static int w(String tag, String msg) {
		if (isdebug) {
			Log.w(tag, msg);
		}
		return 0;
	}

	/**
	 * error 表示打印日志的优先级
	 * 
	 * @param tag
	 *            :过滤条件
	 * @param msg
	 *            :日志内容
	 * @return int
	 */
	public static int e(String tag, String msg) {
		if (isdebug) {
			Log.e(tag, msg);
		}
		return 0;
	}

	/**
	 * 
	 * @param priority
	 *            :日志级别
	 * @param tag
	 *            ：过滤条件
	 * @param msg
	 *            ；信息内容
	 * @return int
	 */
	public static int println(int priority, String tag, String msg) {
		if (isdebug) {
			Log.println(priority, tag, msg);
		}
		return 0;
	}
}
