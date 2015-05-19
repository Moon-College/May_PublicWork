package com.dawndaybreak.logappliction.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

/**
 * log tools class,by isDebug control print.
 * 
 * @author dawn
 *
 */
public class LogUtils {

	/**
	 * log print control flag
	 */
	private static boolean isDebug;

	/**
	 * print an verbose level log message
	 * @param tag
	 * 		by tag identify a log message
	 * @param msg
	 * 		this message is you would like print log
	 * @return
	 */
	public static int verbose(String tag, String msg) {
		if (isDebug) {
			Log.v(tag, msg);
		}
		return 0;
	}

	/**
	 * print an debug level log message
	 * @param tag
	 * 		by tag identify a log message
	 * @param msg
	 * 		this message is you would like print log
	 * @return
	 */
	public static int debug(String tag, String msg) {
		if (isDebug) {
			Log.d(tag, msg);
		}
		return 0;
	}

	/**
	 * print an info level log message
	 * @param tag
	 * 		by tag identify a log message
	 * @param msg
	 * 		this message is you would like print log
	 * @return
	 */
	public static int info(String tag, String msg) {
		if (isDebug) {
			Log.i(tag, msg);
		}
		return 0;
	}

	/**
	 * print an warn level log message
	 * @param tag
	 * 		by tag identify a log message
	 * @param msg
	 * 		this message is you would like print log
	 * @return
	 */
	public static int warn(String tag, String msg) {
		if (isDebug) {
			Log.w(tag, msg);
		}
		return 0;
	}

	/**
	 * print an error level log message
	 * @param tag
	 * 		by tag identify a log message
	 * @param msg
	 * 		this message is you would like print log
	 * @return
	 */
	public static int error(String tag, String msg) {
		if (isDebug) {
			Log.e(tag, msg);
		}
		return 0;
	}
	
	/**
	 * collect specified log level log information
	 * @param level
	 * 		log level
	 * @return
	 */
	public static String collectLogMessageByLevel(String level) {
		if (level == null
				|| "".equals(level)) {
			throw new NullPointerException("this level is null or empty");
		}
		
		StringBuffer result = new StringBuffer();
		
		List<String> cmdLogcat = new ArrayList<String>();
		cmdLogcat.add("logcat");
		cmdLogcat.add("-d");
		cmdLogcat.add("*:" + level);
		
		try {
			Process process = Runtime.getRuntime().exec(
					cmdLogcat.toArray(new String[cmdLogcat.size()]));
			InputStream logIs = process.getInputStream();
			BufferedReader logBr = new BufferedReader(
					new InputStreamReader(logIs));
			String line = null;
			while ((line = logBr.readLine()) != null) {
				result.append(line);
			}
			logBr.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result.toString();
	}
	
	public static final class Level {
		public static final String VERBOSE = "V";
		public static final String DEBUG = "D";
		public static final String INFO = "I";
		public static final String WARN = "W";
		public static final String ERROR = "E";
	}

	public static boolean isDebug() {
		return isDebug;
	}

	public static void setDebug(boolean isDebug) {
		LogUtils.isDebug = isDebug;
	}

}
