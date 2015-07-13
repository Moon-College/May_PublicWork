package com.tz.xmldata.utils;

import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class DataUtils {
	
	/**
	 * ���г������������ݴ洢
	 * @param context
	 * @param fileName
	 * @param map
	 */
	public static void saveData(Context context, String fileName, Map<String, Object> map){
		SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		Editor edit = sharedPreferences.edit();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			//������ֵ��
			String key = entry.getKey();
			Object value = entry.getValue();
			//��value�����жϣ�������ʲô��������
			if(value instanceof Integer){
				edit.putInt(key, (Integer)value);
			}else if(value instanceof String){
				edit.putString(key, (String)value);
			}else if(value instanceof Boolean){
				edit.putBoolean(key, (Boolean)value);
			}else if(value instanceof Long){
				edit.putLong(key, (Long)value);
			}else if(value instanceof Float){
				edit.putFloat(key, (Float)value);
			}
		}
		edit.commit();
	}
	
	/**
	 * ���г������������ݻ�ȡ
	 * @param context
	 * @param fileName
	 * @param key
	 * @param clazz
	 * @return
	 */
	public static Object getData(Context context, String fileName, String key, Class clazz){
		Object obj = null;
		SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		if(clazz.getName().equals(Integer.class.getName())){
			obj = sharedPreferences.getInt(key, 0);
		}else if(clazz.getName().equals(String.class.getName())){
			obj = sharedPreferences.getString(key, "");
		}else if(clazz.getName().equals(Boolean.class.getName())){
			obj = sharedPreferences.getBoolean(key, false);
		}else if(clazz.getName().equals(Long.class.getName())){
			obj = sharedPreferences.getLong(key, 0);
		}else if(clazz.getName().equals(Float.class.getName())){
			obj = sharedPreferences.getFloat(key, 0);
		}
		return obj;
	}

}
