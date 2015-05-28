package com.jianbo.utils;

import android.util.Log;
/**
 * Log日志管理类
 * @author Riders luo
 */
public class RountLog {
	/**
	 * Log日志的开关
	 */
	public static boolean isDebug = false;
	
	//日志打印等级 
	//VEWBOSE
	public static final int VERBOSE = Log.VERBOSE; 
	//DEBUG
	public static final int DEBUG = Log.DEBUG;
	//INFO
	public static final int INFO = Log.INFO;
	//WARN
	public static final int WARN = Log.WARN;
	//ERROR
	public static final int ERROR = Log.ERROR;
	
	//日志类型
	/**
	 * 打印VEWBOSE类型日志
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int v(String tag,String msg){
		if (isDebug) {
			//判断Log日志开关是否打开
			return Log.println(VERBOSE, tag, msg);
		}
		return 0;
	}
	
	/**
	 * 打印DEBUG类型日志
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int d(String tag,String msg){
		if (isDebug) {
			return Log.println(DEBUG, tag, msg);
		}
		return 0;
	}
	
	/**
	 * 打印INFO类型的日志
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int i(String tag,String msg){
		if (isDebug) {
			return Log.println(INFO, tag, msg);
		}
		return 0;
	}
	
	/**
	 * 打印WARN类型的日志
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int w(String tag,String msg){
		if (isDebug) {
			return Log.println(WARN, tag, msg);
		}
		return 0;
	}
	
	/**
	 * 打印ERROR类型的日志
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int e(String tag,String msg){
		if (isDebug) {
			return Log.println(ERROR, tag, msg);
		}
		return 0;
	}
}
