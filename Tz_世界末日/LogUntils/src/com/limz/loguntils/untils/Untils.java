package com.limz.loguntils.untils;

import android.R.integer;
import android.util.Log;

public class Untils {

	public static boolean isLogTrunOn = false;

	/**
	 * Log level : info
	 * @author limingzhu
	 * @param tag : this is the label of the log 
	 * @param msg : this is the information of the log
	 * @return if the switch is turn on, return the value of the Log.i back, else return 0
	 */
	public static int i(String tag, String msg) {
		if(isLogTrunOn) {
			return Log.i(tag, msg);
		} else {
			return 0;
		}
	}
	
	/**
	 * Log level : verbose
	 * @author limingzhu
	 * @param tag : this is the label of the log 
	 * @param msg : this is the information of the log
	 * @return if the switch is turn on, return the value of the Log.v back, else return 0
	 */
	public static int v(String tag, String msg) {
		if(isLogTrunOn) {
			return Log.v(tag, msg);
		} else {
			return 0;
		}
	}
	
	/**
	 * Log level : Warn
	 * @author limingzhu
	 * @param tag : this is the label of the log 
	 * @param msg : this is the information of the log
	 * @return if the switch is turn on, return the value of the Log.w back, else return 0
	 */
	public static int w(String tag, String msg) {
		if(isLogTrunOn) {
			return Log.w(tag, msg);
		} else {
			return 0;
		}
	}
	
	/**
	 * Log level : Error
	 * @author limingzhu
	 * @param tag : this is the label of the log 
	 * @param msg : this is the information of the log
	 * @return if the switch is turn on, return the value of the Log.e back, else return 0
	 */
	public static int e(String tag, String msg) {
		if(isLogTrunOn) {
			return Log.e(tag, msg);
		} else {
			return 0;
		}
	}
	
	/**
	 * Log level : Debug
	 * @author limingzhu
	 * @param tag : this is the label of the log 
	 * @param msg : this is the information of the log
	 * @return if the switch is turn on, return the value of the Log.d back, else return 0
	 */
	public static int d(String tag, String msg) {
		if(isLogTrunOn) {
			return Log.e(tag, msg);
		} else {
			return 0;
		}
	}
	
	/**
	 * this is log
	 * @author limingzhu
	 * @param priority : this is the label of the log
	 * @param tag : this is the label of the log 
	 * @param msg : this is the information of the log
	 * @return if the switch is turn on, return the value of the Log.println back, else return 0
	 */
	public static int println(int priority, String tag, String msg) {
		if(isLogTrunOn) {
			return Log.println(priority, tag, msg);
		} else {
			return 0;
		}
	}
}
