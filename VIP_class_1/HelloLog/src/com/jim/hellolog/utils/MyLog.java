package com.jim.hellolog.utils;

import android.util.Log;

public class MyLog {
	public static boolean isDebug;

	public static int i(String tag, String msg) {
		if (isDebug) {
			return Log.i(tag, msg);
		}
		return 0;
	}

	public static int d(String tag, String msg) {
		if (isDebug) {
			return Log.d(tag, msg);
		}
		return 0;
	}

	public static int e(String tag, String msg) {
		if (isDebug) {
			return Log.e(tag, msg);
		}
		return 0;
	}

	public static int w(String tag, String msg) {
		if (isDebug) {
			return Log.w(tag, msg);
		}
		return 0;
	}

	public static int v(String tag, String msg) {
		if (isDebug) {
			return Log.v(tag, msg);
		}
		return 0;
	}

	public static int println(int priority, String tag, String msg) {
		if (isDebug) {
			return Log.println(priority, tag, msg);

		}
		return 0;
	}
}
