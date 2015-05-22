package com.rocy.logcat.util;

import android.util.Log;

public class MyLog {
	//是否打开日志
    public static boolean isOpenLog;
    public static int INfO =Log.INFO;
    public static int ERROR=Log.ERROR;
    public static int WARN=Log.WARN;
    public static int DEBUG=Log.DEBUG;
    
    public static int i(String tag,String msg){
    	if(isOpenLog){
    		return Log.i(tag, msg);
    		
    	}
		return 0;
    	
    }
    
    public static int e(String tag,String msg){
    	if(isOpenLog){
    		return Log.e(tag, msg);
    	}
		return 0;
    	
    }
    
    public static int println(int priority ,String tag,String msg){
    	if(isOpenLog){
    		return Log.println(priority, tag, msg);
    	}
		return 0;
    	
    }
}
