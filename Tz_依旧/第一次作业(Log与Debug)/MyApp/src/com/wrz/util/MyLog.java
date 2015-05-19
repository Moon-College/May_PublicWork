package com.wrz.util;

import android.util.Log;


public class MyLog {

	public static boolean isDebug;
 
	public static int v(String tag, String msg){
		if(isDebug){
			// verbose日志正常输出
			Log.v(tag, msg);
		}
		return 0;
	}
	
	public static int i(String tag, String msg){
		if(isDebug){
			// info日志正常输出
			Log.i(tag, msg);
		}
		return 0;
	}
	
	public static int d(String tag, String msg){
		if(isDebug){
			// debug日志正常输出
			Log.d(tag, msg);
		}
		return 0;
	}
	
	public static int w(String tag, String msg){
		if(isDebug){
			// warn日志正常输出
			Log.w(tag, msg);
		}
		return 0;
	}
	
	public static int e(String tag, String msg){
		if(isDebug){
			// error日子正常输出
			Log.e(tag, msg);
		}
		return 0;
	}
	
	public static int println(int priority, String tag, String msg){
		if(isDebug){
			Log.println(priority, tag, msg);
		}
		return 0;
	}
}
