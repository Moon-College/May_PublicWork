package com.lin.mytzwork.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Created by lin on 15-7-10.
 */
public class DataUtils {
    private static final String cfgName = "config";

    public static void saveData(Context context, String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences(cfgName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(key, value);
        edit.commit();
    }

    public static void saveData(Context context, Map<String, String> mapValue) {
        SharedPreferences preferences = context.getSharedPreferences(cfgName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        for (Map.Entry<String, String> map : mapValue.entrySet()) {
            edit.putString(map.getKey(), map.getValue());
        }
        edit.commit();
    }


    public static String readData(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(cfgName, Context.MODE_PRIVATE);
        return preferences.getString(key, "");
    }


}
