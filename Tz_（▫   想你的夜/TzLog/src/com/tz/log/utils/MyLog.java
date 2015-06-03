package com.tz.log.utils;

import android.util.Log;

public class MyLog {
	public static boolean isdebug; // ��־����

	/**
	 * verbose ��ʾ��ӡ��־�����ȼ� tag���������� msg����־����
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
	 * debug ��ʾ��ӡ��־�����ȼ� tag���������� msg����־����
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
	 * info ��ʾ��ӡ��־�����ȼ�
	 * 
	 * @param tag
	 *            :��������
	 * @param msg
	 *            :��־ ����
	 * @return int
	 */
	public static int i(String tag, String msg) {
		if (isdebug) {
			return Log.i(tag, msg);
		}
		return 0;
	}

	/**
	 * warn ��ʾ��ӡ��־�����ȼ�
	 * 
	 * @param tag
	 *            :��������
	 * @param msg
	 *            :��־����
	 * @return int
	 */
	public static int w(String tag, String msg) {
		if (isdebug) {
			Log.w(tag, msg);
		}
		return 0;
	}

	/**
	 * error ��ʾ��ӡ��־�����ȼ�
	 * 
	 * @param tag
	 *            :��������
	 * @param msg
	 *            :��־����
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
	 *            :��־����
	 * @param tag
	 *            ����������
	 * @param msg
	 *            ����Ϣ����
	 * @return int
	 */
	public static int println(int priority, String tag, String msg) {
		if (isdebug) {
			Log.println(priority, tag, msg);
		}
		return 0;
	}
}
