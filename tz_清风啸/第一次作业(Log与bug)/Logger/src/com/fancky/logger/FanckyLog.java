package com.fancky.logger;

import android.util.Log;

/**
 * 
 * @author Fancky
 * @date 2015-5-18
 * @description 
 * 			log manager
 */
public class FanckyLog {

	/**
	 * Priority constant for the println method; use FanckyLog.v.
	 */
	public static final int VERBOSE = 2;

	/**
	 * Priority constant for the println method; use FanckyLog.d.
	 */
	public static final int DEBUG = 3;

	/**
	 * Priority constant for the println method; use FanckyLog.i.
	 */
	public static final int INFO = 4;

	/**
	 * Priority constant for the println method; use FanckyLog.w.
	 */
	public static final int WARN = 5;

	/**
	 * Priority constant for the println method; use FanckyLog.e.
	 */
	public static final int ERROR = 6;
	/**
	 * print log switch
	 */
	public static boolean isPrintLog;
	/**
	 * log level,the default is verbose
	 */
	public static int level = VERBOSE;
	
	/**
	 * Send a log message, level is verbose.
	 * @param tag tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
	 * @param msg The message you would like logged.
	 * @return
	 */
	public static int v(String tag, String msg) {
		if (isPrintLog && level <= VERBOSE) {
			return Log.v(tag, msg);
		}
		return 0;
	}
	
	/**
	 * Send a log message, level is debug.
	 * @param tag tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
	 * @param msg The message you would like logged.
	 * @return
	 */
	public static int d(String tag, String msg) {
		if (isPrintLog && level <= DEBUG) {
			return Log.d(tag, msg);
		}
		return 0;
	}
	
	/**
	 * Send a log message, level is info.
	 * @param tag tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
	 * @param msg The message you would like logged.
	 * @return
	 */
	public static int i(String tag, String msg) {
		if (isPrintLog && level <= INFO) {
			return Log.i(tag, msg);
		}
		return 0;
	}
	
	/**
	 * Send a log message, level is warn.
	 * @param tag tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
	 * @param msg The message you would like logged.
	 * @return
	 */
	public static int w(String tag, String msg) {
		if (isPrintLog && level <= WARN) {
			return Log.w(tag, msg);
		}
		return 0;
	}
	
	/**
	 * Send a log message, level is error.
	 * @param tag tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
	 * @param msg The message you would like logged.
	 * @return
	 */
	public static int e(String tag, String msg) {
		if (isPrintLog && level <= ERROR) {
			return Log.e(tag, msg);
		}
		return 0;
	}
	
	 /**
     * 
     * @param level The level/type of this log message
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @return The number of bytes written.
     */
	public static int println(int level, String tag, String msg) {

		switch (level) {
		case VERBOSE:
			return Log.v(tag, msg);
		
		case DEBUG:
			return Log.d(tag, msg);
			
		case INFO:
			return Log.i(tag, msg);
			
		case WARN:
			return Log.w(tag, msg);
			
		case ERROR:
			return Log.e(tag, msg);

		default:
			return 0;
		}

		
	}
	
}
