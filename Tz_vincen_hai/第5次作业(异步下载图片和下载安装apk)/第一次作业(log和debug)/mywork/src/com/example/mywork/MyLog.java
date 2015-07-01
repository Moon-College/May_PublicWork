package com.example.mywork;

import android.util.Log;


public class MyLog {
	
	public static boolean isDebug = false;
	
	public static int i(String tag,String msg){
		if(isDebug){
			Log.i(tag, msg);
		}
		return 0;
	}
	
	 public static int v(String tag, String msg) {
		 if(isDebug){
			 Log.v(tag, msg);
		 }
		 return 0;
	 }
	 
	 public static int d(String tag, String msg) {
		 if(isDebug){
			 Log.d(tag, msg);
		 }
		 return 0;
	 }
	 
	 public static int e(String tag, String msg) {
		 if(isDebug){
			 Log.e(tag, msg);
		 }
		 return 0;
	 }
	 
	 public static int w(String tag, String msg) {
		 if(isDebug){
			 Log.w(tag, msg);
		 }
		 return 0;
	 }


}