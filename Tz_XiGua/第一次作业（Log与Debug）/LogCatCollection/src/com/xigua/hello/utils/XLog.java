package com.xigua.hello.utils;

import android.util.Log;

public class XLog {
	
	public static boolean sDebug;

	/**
	 * Log.v
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int v(String tag,String msg){
		if(sDebug){
			return Log.v(tag, msg);
		}
		else{
			return 0;
		}
	}
	
	/**
	 * Log.d
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int d(String tag,String msg){
		if(sDebug){
			return Log.d(tag, msg);
		}
		else{
			return 0;
		}
	}
	
	/**
	 * Log.i
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int i(String tag,String msg){
		if(sDebug){
			return Log.i(tag, msg);
		}
		else{
			return 0;
		}
	}
	
	/**
	 * Log.w
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int w(String tag,String msg){
		if(sDebug){
			return Log.w(tag, msg);
		}
		else{
			return 0;
		}
	}
	
	/**
	 * Log.e
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int e(String tag,String msg){
		if(sDebug){
			return Log.e(tag, msg);
		}
		else{
			return 0;
		}
	}
	
	/**
	 * Log.println
	 * @param priority
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int println(int priority,String tag,String msg){
		if(sDebug){
			return Log.println(priority, tag, msg);
		}
		else {
			return 0;
		}
	}
	

}
