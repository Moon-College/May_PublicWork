package com.tz.collectionlog.utils;


import android.util.Log;

/**
 * 日志管理类
 * @author fcc
 *
 */
public class MyLog {
	/** 日志的开关 */
	public static boolean ISDUG;
	
	/**
	 * verbose
	 * @param tag 级别
	 * @param msg 日志信息
	 * @return
	 */
	public static int v(String tag, String msg){
		if(ISDUG){
			return Log.i(tag,msg);
		}
		return 0;
	}
	
	/**
	 * debug
	 * @param tag 级别
	 * @param msg 日志信息
	 * @return
	 */
	public static int d(String tag, String msg){
		if(ISDUG){
			Log.d(tag, msg);
		}
		return 0;
	}
	/**
	 * info
	 * @param tag 级别
	 * @param msg 日志信息
	 * @return
	 */
	public static int i(String tag, String msg){
		if(ISDUG){
			return Log.i(tag,msg);
		}
		return 0;
	}
	
	/**
	 * warn日志
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int w(String tag, String msg){
		if(ISDUG){
			return Log.w(tag, msg);
		}
		return 0;
	}
	
	/**
	 * error
	 * @param tag 级别
	 * @param msg 日志信息
	 * @return
	 */
	public static int e(String tag, String msg){
		if(ISDUG){
			return Log.e(tag, msg);
		}
		return 0;
	}
}
