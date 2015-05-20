package com.xby.mylog.util;

import android.util.Log;

public class MyLog {
	public static boolean flag;
	public static int log(int priority,String tag,String msg){
		if(flag){
			return Log.println(priority, tag, msg);
		}
		return 0;
	}
}
