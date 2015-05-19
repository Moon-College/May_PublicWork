package com.example.com.tz.ssk.mylog;

import android.util.Log;

public class LogUtils {

	public static boolean isDebug;
	
	public static void myPrintln(int priority,String tag,String msg)
	{
		if(isDebug)
		{
			Log.println(priority, tag, msg);
		}
	}
	public static void i(String tag,String msg)
	{
		if(isDebug)
		{
			Log.i(tag, msg);
		}
	}
	public static void d(String tag,String msg)
	{
		if(isDebug)
		{
			Log.d(tag, msg);
		}
	}
	public static void w(String tag,String msg)
	{
		if(isDebug)
		{
			Log.w(tag, msg);
		}
	}
}
