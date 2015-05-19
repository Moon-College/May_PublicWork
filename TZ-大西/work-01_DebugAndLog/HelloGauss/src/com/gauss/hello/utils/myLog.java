package com.gauss.hello.utils;

import android.util.Log;


public class myLog {
    public static boolean isDebug;//日志开关
    
    public static int iLog(String tag,String msg){
        if(isDebug){
           return Log.i(tag, msg);//开启日志是进入
        }
        else{
        	return 0;
        } 	
    }
    
    public static int dLog(String tag,String msg){
        if(isDebug){
        	return Log.d(tag,msg);
        }
        else{
        	return 0;
        }
    }
    
    public static int eLog(String tag,String msg){
    	if(isDebug){
    		return Log.e(tag, msg);
    	}
    	else{
    		return 0;
    	}
    }
    
    public static int wLog(String tag,String msg){
    	if(isDebug){
    		return Log.w(tag, msg);
    	}
    	else{
    		return 0;
    	}
    }
    
    public static int vLog(String tag,String msg){
    	if(isDebug){
    		return Log.v(tag, msg);
    	}
    	else{
    		return 0;
    	}
    }
    
    /**
     * Print all type of log
     * @param priority Log type
     * @param tag 
     * @param msg
     * @return
     */
    public static int printLong(int priority,String tag,String msg){
    	if(isDebug){
    		return Log.println(priority, tag, msg);
    	}
    	else{
    		return 0;
    	}
    }

}
