package com.tz.mylogrunntime.util;

import android.util.Log;

public class UtilLog {
	
	public static final boolean DEBUG = true;
	
	public static void i(String tag, Object obj) {
		if(DEBUG) {
			Log.i(tag, obj.toString());
		}
	}
	
	public static void d(String tag, Object obj) {
		if(DEBUG) {
			Log.d(tag, obj.toString());
		}
	}
	
	public static void w(String tag, Object obj) {
		if(DEBUG) {
			Log.w(tag, obj.toString());
		}
	}
	
	public static void e(String tag, Object obj) {
		if(DEBUG) {
			Log.e(tag, obj.toString());
		}
	}
	
	public static void v(String tag, Object obj) {
		if(DEBUG) {
			Log.v(tag, obj.toString());
		}
	}
}
