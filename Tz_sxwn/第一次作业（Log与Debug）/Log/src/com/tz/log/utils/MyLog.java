package com.tz.log.utils;

import android.util.Log;

public class MyLog {
	public static boolean isDebug;//日志的开关
	public static int i(String tag,String msg){
		if(isDebug){
			return Log.i(tag, msg);
		}
		return 0;
	}
	
	
	public static int e(String tag,String msg){
		if(isDebug){
			return Log.e(tag, msg);
		}
		return 0;
	}
	
	
	public static int w(String tag,String msg){
		if(isDebug){
			return Log.w(tag, msg);
		}
		return 0;
	}
	
	
	public static int d(String tag,String msg){
		if(isDebug){
			return Log.d(tag, msg);
		}
		return 0;
	}
	
	
	public static int println(int priority,String tag,String msg){
		if(isDebug){
			return Log.println(priority, tag, msg);
		}
		return 0;
	}
}
