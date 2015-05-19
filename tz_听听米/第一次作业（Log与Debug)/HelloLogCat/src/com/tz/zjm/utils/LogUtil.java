package com.tz.zjm.utils;

import android.util.Log;

public class LogUtil {

	public static boolean isDebug = true;
	
	
	public static int println(int priority,String tag,String msg){
		if(isDebug){
			return Log.println(priority, tag, msg);
		}
		return 0;
	}
	
	public static int v(String tag,String msg){
		if(isDebug){
			return println(Log.VERBOSE,tag,msg);
		}
		return 0 ;
	}
	
	public static int d(String tag,String msg){
		if(isDebug){
			return println(Log.DEBUG,tag,msg);
		}
		return 0 ;
	}
	
	public static int i(String tag,String msg){
		if(isDebug){
			return println(Log.INFO,tag,msg);
		}
		return 0 ;
	}
	
	public static int w(String tag,String msg){
		if(isDebug){
			return println(Log.WARN,tag,msg);
		}
		return 0 ;
	}
	
	public static int e(String tag,String msg){
		if(isDebug){
			return println(Log.ERROR,tag,msg);
		}
		return 0 ;
	}
	
	public static int a(String tag,String msg){
		if(isDebug){
			return println(Log.ASSERT,tag,msg);
		}
		return 0 ;
	}
	
}
