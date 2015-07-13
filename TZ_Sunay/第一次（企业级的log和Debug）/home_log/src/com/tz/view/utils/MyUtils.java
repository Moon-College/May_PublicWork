
package com.tz.view.utils;

import android.util.Log;

public class MyUtils {
    public static boolean ISDUG ;
   
    public static int v(String tag, String msg){
    	if(ISDUG){
           return Log.i(tag, msg);
    	}
		return 0;
    	
    }
    
    public static int d(String tag, String msg){
    	if(ISDUG){
           return Log.i(tag, msg);
    	}
		return 0;
    	
    }
    
    public static int i(String tag, String msg){
    	if(ISDUG){
           return Log.i(tag, msg);
    	}
		return 0;
    	
    }
    
    public static int w(String tag, String msg){
    	if(ISDUG){
           return Log.i(tag, msg);
    	}
		return 0;
    	
    }
    
    public static int e(String tag, String msg){
    	if(ISDUG){
           return Log.i(tag, msg);
    	}
		return 0;
    	
    }
    
    public static int a(String tag, String msg){
    	if(ISDUG){
           return Log.i(tag, msg);
    	}
		return 0;
    	
    }
	
	 
}
