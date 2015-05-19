package com.tz.log.util;

import android.util.Log;

/**
 * Created by qinhan on 15/5/19.
 */
public class LogUtil {
    private static final boolean isDebug = true;

    public static String DEFAULT_TAG = "LOG";

    public static int printLog(String info) {
        return printLog(DEFAULT_TAG, info);
    }

    public static int printLog(String tag, String info) {
        return printLog(Log.VERBOSE, tag, info);
    }

    public static int printLog(int priority, String tag, String info) {
        if (isDebug) {
            return Log.println(priority, tag, info);
        }
        return -1;
    }
}
