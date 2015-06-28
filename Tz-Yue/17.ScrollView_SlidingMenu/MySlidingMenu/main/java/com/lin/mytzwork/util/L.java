package com.lin.mytzwork.util;

import android.util.Log;

public class L {

    private static boolean isDebut = true;


    public static int i(String tag, String msg) {
        if (isDebut) {
            return Log.i(tag, msg);
        }
        return 0;
    }

    public static int i(String tag, String msg, Throwable tr) {
        if (isDebut) {
            return Log.i(tag, msg, tr);
        }
        return 0;
    }

    public static int d(String tag, String msg) {
        if (isDebut) {
            return Log.d(tag, msg);
        }
        return 0;
    }

    public static int d(String tag, String msg, Throwable tr) {
        if (isDebut) {
            return Log.d(tag, msg, tr);
        }
        return 0;
    }

    public static int w(String tag, String msg) {
        if (isDebut) {
            return Log.w(tag, msg);
        }
        return 0;
    }

    public static int w(String tag, String msg, Throwable tr) {
        if (isDebut) {
            return Log.w(tag, msg, tr);
        }
        return 0;
    }

    public static int e(String tag, String msg) {
        if (isDebut) {
            return Log.e(tag, msg);
        }
        return 0;
    }

    public static int e(String tag, String msg, Throwable tr) {
        if (isDebut) {
            return Log.e(tag, msg, tr);
        }
        return 0;
    }

}