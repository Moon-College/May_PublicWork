package com.zt.ztt.utils;

import android.util.Log;

public class LogUtils {

	public static final String ERROR = "ERROE";
	public static final String INFO = "INFO";
	public static boolean isDebug;

	/***
	 * Êä³ö INFO
	 * 
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int i(String tag, String msg) {
		if (isDebug) {
			return Log.i(tag, msg);
		}

		return 0;
	}

	/***
	 * Êä³ö DEBUG
	 * 
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int d(String tag, String msg) {
		if (isDebug) {
			return Log.i(tag, msg);
		}

		return 0;
	}

	/***
	 * Êä³ö ERROR
	 * 
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int e(String tag, String msg) {
		if (isDebug) {
			return Log.i(tag, msg);
		}

		return 0;
	}

}
