package com.itskylin.android.mylog.utils;

import android.util.Log;


/**
 * ClassName: LogUtils
 * @Description: 自定义Log
 * @author BlueSky QQ：345066543
 * @date 2015年5月26日
 */
public class LogUtils {

	public static boolean isDebug = true;
	
	/**
	 * @Description:参考{@link Log#println(int, String, String)}
	 * @throws null
	 * @author BlueSky QQ：345066543
	 * @date 2015年5月26日
	 * 
	 */
	public static int i(int logLevel,String tag,String msg){
		if(isDebug){
		return Log.println(logLevel, tag, msg);
		}
		return -1;
	}
}

