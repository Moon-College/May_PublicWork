package com.tz.ays.michael.utils;

import android.util.Log;
/**
 * log工具类，可以通过设置LogUtils.isDebug来动态的设置是否开启log
 * @author szm
 *
 */
public class LogUtils {

	/**定义log的开关*/
	public static boolean isDebug=true;
	
	/**
	 * info级别的log
	 * @param tag 过滤条件
	 * @param msg 日志信息
	 * @return int 0失败  其他 成功
	 */
	public static int i(String tag,String msg){
		if(isDebug){
			return Log.i(tag, msg);
		}
		return 0;
	}
	
	/**
	 * error 级别的log
	 * @param tag 过滤条件
	 * @param msg 日志信息
	 * @return int 0失败  其他 成功
	 */
	public static int e(String tag,String msg){
		if(isDebug){
			return Log.e(tag, msg);
		}
		return 0;
	}
	
	/**
	 * WARN 级别 
	 * @param tag 过滤条件
	 * @param msg 日志信息
	 * @return int 0失败  其他 成功
	 */
	public static int w(String tag,String msg){
		if(isDebug){
			return Log.w(tag, msg);
		}
		return 0;
	}
	
	/**
	 * debug 级别
	 * @param tag 过滤条件
	 * @param msg 日志信息
	 * @return int 0失败  其他 成功
	 */
	public static int d(String tag,String msg){
		if(isDebug){
			return Log.d(tag, msg);
		}
		return 0;
	}
	
	/**
	 * 通用log方法
	 * @param priority 信息级别（int）fg:Log.INFO etc
	 * @param tag 过滤条件
	 * @param msg 日志信息
	 * @return int 0失败  其他 成功
	 */
	public static int println(int priority,String tag,String msg){
		if(isDebug){
			return Log.println(priority, tag, msg);
		}
		return 0;
	}
}
