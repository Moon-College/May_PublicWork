package com.decent.decentrefliction.util;

import android.util.Log;

public class CustomLog
{
	//用于标识现在打印打印
	private static boolean mNowDebugFlag = true;
	
	/**
	 * 设置当前是否打印日志
	 * @param isNowDebug 是否现在打印日志
	 */
    public static void setNowDebugFlag(boolean isNowDebug)
    {
    	mNowDebugFlag = isNowDebug;
    }
    
    /**
     * 获取当前是否打印日志
     * @return 当前是否打印日志
     */
    public static boolean getNowDebugFlag()
    {
    	return mNowDebugFlag;
    }
    
    /**
     * 打印INFO级别的log
     * @param tag log的TAG
     * @param msg log内容
     * @return 返回打印日志是否成功
     */
    public static int i(String tag, String msg)
    {
    	if(mNowDebugFlag)
    	{
    		return Log.i(tag,msg);
    	}
    	return 0;
    }
    
    /**
     * 打印DEBUG级别的log
     * @param tag log的TAG
     * @param msg log内容
     * @return 返回打印日志是否成功
     */
    public static int d(String tag, String msg)
    {
    	if(mNowDebugFlag)
    	{
    		return Log.d(tag, msg);
    	}
    	return 0;
    }
    
    /**
     * 打印WARN级别的log
     * @param tag log的TAG
     * @param msg log内容
     * @return 返回打印日志是否成功
     */
    public static int w(String tag, String msg)
    {
    	if(mNowDebugFlag)
    	{
    		return Log.w(tag, msg);
    	}
    	return 0;
    }
    
    /**
     * 打印ERROR级别的log
     * @param tag log的TAG
     * @param msg log内容
     * @return 返回打印日志是否成功
     */
    public static int e(String tag, String msg)
    {
    	if(mNowDebugFlag)
    	{
    		return Log.e(tag, msg);
    	}
    	return 0;
    }
}
