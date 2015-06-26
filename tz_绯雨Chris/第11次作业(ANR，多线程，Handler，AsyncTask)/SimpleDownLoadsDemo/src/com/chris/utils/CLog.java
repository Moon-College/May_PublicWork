/**
 * utilities to control log message.
 * @author ChrisLiu
 */
package com.chris.utils;

import android.util.Log;

public class CLog
{
	public static boolean isDebug = true;

	/**
	 * To judge wether the debug status is open or closed.
	 * Default status is open.
	 * @return isDebug status, open or closed
	 */
	public static boolean isDebug()
	{
		return isDebug;
	}

	/**
	 * open or close the Clog debug message.
	 * @param isDebug
	 */
	public static void setDebug(boolean isDebug)
	{
		CLog.isDebug = isDebug;
	}

	/**
	 * Priority constant for the println method; use Log.v.
	 */
	public static final int VERBOSE = 2;

	/**
	 * Priority constant for the println method; use Log.d.
	 */
	public static final int DEBUG = 3;

	/**
	 * Priority constant for the println method; use Log.i.
	 */
	public static final int INFO = 4;

	/**
	 * Priority constant for the println method; use Log.w.
	 */
	public static final int WARN = 5;

	/**
	 * Priority constant for the println method; use Log.e.
	 */
	public static final int ERROR = 6;

	/**
	 * Priority constant for the println method.
	 */
	public static final int ASSERT = 7;

	/**
	 * base function for log printing
	 */
	public static int println(int priority, String tag, String msg)
	{
		if (isDebug)
		{
			return Log.println(priority, tag, msg);
		}
		return 0;
	}

	/**
	 * verbose messages
	 */
	public static int v(String tag, String msg)
	{
		return println(VERBOSE, tag, msg);
	}

	/**
	 * debug messages
	 */
	public static int d(String tag, String msg)
	{
		return println(DEBUG, tag, msg);
	}

	/**
	 * info messages
	 */
	public static int i(String tag, String msg)
	{
		return println(INFO, tag, msg);
	}

	/**
	 * warning messages
	 */
	public static int w(String tag, String msg)
	{
		return println(WARN, tag, msg);
	}

	/**
	 * error messages
	 */
	public static int e(String tag, String msg)
	{
		return println(ERROR, tag, msg);
	}

}
