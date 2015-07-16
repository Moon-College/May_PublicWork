package com.lin.mytzwork.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Created by Administrator on 2015/7/13.
 */
public class ShareDataUtil {
    private static final String XML_NAME = "config";

    public static void saveData(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(XML_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, value);
        edit.commit();
    }


    public static void saveData(Context context, Map<String, String> stringMap) {
        SharedPreferences sp = context.getSharedPreferences(XML_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        for (Map.Entry<String, String> map : stringMap.entrySet()) {
            String key = map.getKey();
            String value = map.getValue();
            edit.putString(key, value);
        }
        edit.commit();
    }


    public static String readData(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(XML_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }


}
