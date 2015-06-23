/**
 * Project Name:lsn2_log
 * File Name:Config.java
 * Package Name:com.zht.log
 * Date:2015-6-3下午2:43:52
 * Copyright (c) 2015, shixi_hongtao@staff.sina.com.cn All Rights Reserved.
 *
 */

package com.zht.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

/**
 * ClassName:Config <br/>
 * Function: Log类. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-6-3 下午2:43:52 <br/>
 * 
 * @author hongtao
 * @version
 * @since JDK 1.6
 * @see
 */
public class Config {
	public static boolean isDebug;

	// ----------------------------log-----------------------------------
	public static void i(Object msg) {
		if (isDebug) {
			Log.i(getTag(4), msg == null ? "log is null" : msg.toString());
		}
	}

	public static String getTag(int level) {
		StackTraceElement ste = Thread.currentThread().getStackTrace()[level];
		StringBuilder sb = new StringBuilder();
		sb.append(getSimpleClassName(ste.getClassName()));
		sb.append('.');
		sb.append(ste.getMethodName());
		sb.append('(');
		sb.append(ste.getLineNumber());
		sb.append(')');
		return sb.toString();

	}

	public static String getSimpleClassName(String path) {
		int index = path.lastIndexOf('.');
		if (index < 0) {
			return path;
		} else {
			return path.substring(index + 1);
		}
	}

	public static void showTip(Context context, String msg) {
		Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT)
				.show();
	}

	public static void showTip(Context context, int msg) {
		Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT)
				.show();
	}

	// ---------------------------readLog---------------------------------------

	public static String readLog(Context context) throws IOException {
		StringBuffer sb = new StringBuffer();
		ArrayList<String> mList = new ArrayList<String>();

		mList.add("logcat");
		mList.add("-d");
		// mList.add("-s");
		//mList.add("*:i");//通过级别过滤
		mList.add(getTag(4));

		Process exec = Runtime.getRuntime().exec(
				mList.toArray(new String[mList.size()]));
		InputStream inputStream = exec.getInputStream();
		InputStreamReader isr = new InputStreamReader(inputStream);
		BufferedReader br = new BufferedReader(isr);
		String str = null;
		while ((str = br.readLine()) != null) {
			sb.append(str);
			sb.append("\n");
		}

		if (!TextUtils.isEmpty(sb.toString())) {
			return sb.toString();
		}
		return "log is empty";
	}
}
