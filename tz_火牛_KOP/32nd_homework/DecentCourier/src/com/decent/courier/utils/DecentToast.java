package com.decent.courier.utils;

import android.content.Context;
import android.widget.Toast;

public class DecentToast {
	/**
	 * ��ʾToast�����֣�ʱ���
	 * @param context ������
	 * @param msg ����
	 */
	public static void showToastLong(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}
	/**
	 * ��ʾToast�����֣�ʱ�䳤
	 * @param context ������
	 * @param msg ����
	 */
	public static void showToastShort(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	/**
	 * ��ʾToast��layout�ļ���ʱ�䳤
	 * @param context ������
	 * @param resId ��Դ�ļ�id
	 */
	public static void showToastLong(Context context, int resId) {
		Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
	}

	/**
	 * ��ʾToast��layout�ļ���ʱ���
	 * @param context ������
	 * @param resId ��Դ�ļ�id
	 */
	public static void showToastShort(Context context, int resId) {
		Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
	}
}
