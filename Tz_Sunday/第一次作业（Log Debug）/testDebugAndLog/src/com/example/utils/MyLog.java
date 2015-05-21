package com.example.utils;

import android.util.Log;

public class MyLog {
    public  static boolean isDebug;
    
    public static int i(String tag,String msg){
    	if(isDebug){
    		return Log.i(tag, msg);
    	}
    	return 0;
    }
    public static int d(String tag,String msg){
    	if(isDebug){
    	    return Log.i(tag, msg);
    	}
        return 0;
    }
	  
}
