package com.tz.dingding.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class LogUtil {
	public static boolean isDebug; // 日志的开关

	public static int println(int priority, String tag, String msg) {
		if (isDebug) {
			return Log.println(priority, tag, msg);
		}
		return 0;
	}

	public static int v(String tag, String msg) {
		return println(Log.VERBOSE, tag, msg);
	}

	public static int d(String tag, String msg) {
		return println(Log.DEBUG, tag, msg);
	}

	public static int i(String tag, String msg) {
		return println(Log.INFO, tag, msg);
	}

	public static int w(String tag, String msg) {
		return println(Log.WARN, tag, msg);
	}

	public static int e(String tag, String msg) {
		return println(Log.ERROR, tag, msg);
	}

	public static void shortToast(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}
}
