package com.slm.log_manager.util;

import android.util.Log;
public class MyLog {
	/**日志的开关*/
	public static boolean ISDEBUG;
	public static int i(String tag,String msg){
		if (ISDEBUG) {
			return Log.i(tag, msg);
		}
		return 0;
	}
	public static int d(String tag,String msg){
		if (ISDEBUG) {
			return Log.i(tag, msg);
		}
		return 0;
	}
}
