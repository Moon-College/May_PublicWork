package com.junwen.util;

import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.nfc.tech.IsoDep;

public class UserDataUtil {

	/**
	 * 
	 * @param context
	 * @param fileName
	 * @param mode
	 * @param map
	 */
	public static void saveUserData(Context context,String fileName,int mode,Map<String, Object> map){
		SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, mode);
		Editor edit = sharedPreferences.edit();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if(value instanceof  String ){
				edit.putString(key, value.toString());
			}
			else if(value instanceof Integer ){
				edit.putInt(key,(Integer)value);
			}
			else if(value instanceof Boolean ){
				edit.putBoolean(key, (Boolean)value);
			}
			else if(value instanceof Float ){
				edit.putFloat(key, (Float)value);
			}
			else if(value instanceof Long ){
				edit.putLong(key, (Long)value);
			}
		}
		edit.commit();
	}
	/**
	 * 取出xml文件
	 * @param context
	 * @param fileName
	 * @param mode
	 * @return
	 */
	public static  Object getUserData(Context context,String fileName,int mode,String key) {
		Object object = null;
		SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, mode);
		if(sharedPreferences != null){
			Map<String, ?> map = sharedPreferences.getAll();
			if(map.size()>0){
				for (int i = 0; i < map.size(); i++) {
					Object value = map.get(key);
					if(value instanceof String){
						object =  sharedPreferences.getString(key, "");
					}
					else if(value instanceof Boolean) {
						object =  sharedPreferences.getBoolean(key, false);
					}
					else if(value instanceof Float) {
						object =  sharedPreferences.getFloat(key, 0);
					}
					else if(value instanceof Long) {
						object = sharedPreferences.getLong(key, 0);
					}
					else if(value instanceof Integer) {
						object = sharedPreferences.getInt(key, 0);
					}
				}
			}
		}
		 return object;
	}
}
