package com.first.work.logjar.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.R;
import android.content.Context;
import android.util.Log;

/**
 * 
 * @author liyawei 打印日志的工具类
 */
public class MyLog {
	/**
	 * 如果为false就打印日志，否则不打印日志
	 */
	public static boolean isDebug = false;

	/**
	 * @author liyawei
	 * @param log_level
	 *            Log的级别
	 * @param tag
	 *            Log的标签
	 * @param content
	 *            Log的内容
	 */
	public static void printMyLog(int log_level, String tag, String content) {
		if (!isDebug) {
			return;
		}
		switch (log_level) {
		case 2:
			Log.v(tag, content);
			break;
		case 3:
			Log.d(tag, content);
			break;
		case 4:
			Log.i(tag, content);
			break;
		case 5:
			Log.w(tag, content);
			break;
		case 6:
			Log.e(tag, content);
			break;
		default:
			Log.i(tag, content);
			break;
		}
	}

	public static void ClearLogInformation() throws IOException {
		List<String> list = new ArrayList<String>();
		list.add("logcat");
		list.add("-c");
		Runtime.getRuntime().exec(list.toArray(new String[list.size()]));
	}

}
