package com.hq.ays.utils;

import android.util.Log;

public class MyLog {
	public static boolean DEBUG;
	
	public static void d(String msg){
		if(DEBUG){
			Log.d("DEBUG", msg);
		}
	}
	
	public static void i(String msg){
		if(DEBUG){
			Log.i("INFO", msg);
		}
	}
	
}
