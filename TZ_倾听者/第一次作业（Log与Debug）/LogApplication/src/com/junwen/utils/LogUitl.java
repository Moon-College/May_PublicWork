package com.junwen.utils;

import android.util.Log;

public class LogUitl {
	// 是否开启Log
	public static boolean flag = true;
	
	public static int i(String tag, String msg) {

		if (flag == true) {
			return Log.i(tag, msg);
		}
		return 0;
	}

	public static int d(String tag, String msg) {

		if (flag == true) {
			return Log.d(tag, msg);
		}
		return 0;
	}

	public static int w(String tag, String msg) {

		if (flag == true) {
			return Log.w(tag, msg);
		}
		return 0;
	}

	public static int e(String tag, String msg) {

		if (flag == true) {
			return Log.e(tag, msg);
		}
		return 0;
	}

	public static int v(String tag, String msg) {

		if (flag == true) {
			return Log.v(tag, msg);
		}
		return 0;
	}
}
