package com.example.lesson2.util;

import android.util.Log;

/** 
 * @author Carlos
 * @version 1.0
 * @updateTime 2015年5月19日 上午1:49:16
 * Description: 
 */
public class AppLog {
	public static boolean isDebug;
	
	public static int i(String tag,String msg){
		if(isDebug){
			return Log.i(tag, msg);
		}
		return 0;
	}
	
	public static int d(String tag,String msg){
		if(isDebug){
			return Log.d(tag, msg);
		}
		return 0;
	}
	
	public static int w(String tag,String msg){
		if(isDebug){
			return Log.w(tag, msg);
		}
		return 0;
	}
	
	public static int v(String tag,String msg){
		if(isDebug){
			return Log.v(tag, msg);
		}
		return 0;
	}
	
	public static int e(String tag,String msg){
		if(isDebug){
			return Log.e(tag, msg);
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
