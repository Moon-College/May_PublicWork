package com.decent.courier.utils;

import android.content.Context;
import android.widget.Toast;

public class DecentToast {
	/**
	 * 显示Toast，文字，时间短
	 * @param context 上下文
	 * @param msg 文字
	 */
	public static void showToastLong(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}
	/**
	 * 显示Toast，文字，时间长
	 * @param context 上下文
	 * @param msg 文字
	 */
	public static void showToastShort(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 显示Toast，layout文件，时间长
	 * @param context 上下文
	 * @param resId 资源文件id
	 */
	public static void showToastLong(Context context, int resId) {
		Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
	}

	/**
	 * 显示Toast，layout文件，时间短
	 * @param context 上下文
	 * @param resId 资源文件id
	 */
	public static void showToastShort(Context context, int resId) {
		Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
	}
}
