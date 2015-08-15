package com.tz.ays.michael.utils;

import java.util.Map;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * SharedPreferences的工具类
 * @author michael&shi
 *
 */
public class SharedPreferenceUtil {

	/**
	 * 写入SharedPreferences
	 * @param context 上下文
	 * @param fileName 文件的名字
	 * @param map 需要保存的键值对组成的map
	 */
	public static void writeToSP(Context context,String fileName,Map<String,Object> map){
		SharedPreferences sp=context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
		Editor editor=sp.edit();
		for(Map.Entry<String, Object> entry:map.entrySet()){
			String key=entry.getKey();
			Object value=entry.getValue();
			if(value instanceof String){
				editor.putString(key, (String)value);
			}else if(value instanceof Boolean){
				editor.putBoolean(key, (Boolean)value);
			}else if(value instanceof Float){
				editor.putFloat(key, (Float)value);
			}else if(value instanceof Long){
				editor.putLong(key, (Long)value);
			}else if(value instanceof Integer){
				editor.putInt(key, (Integer)value);
			}
		}
		editor.commit();
	}
	
	/**
	 * 从SharedPreferences读取数据
	 * @param context 上下文
	 * @param fileName 文件的名字
	 * @param key 所要查询的key
	 * @param clazz 要读取的值得类型
	 * @return
	 */
	public static Object getValueFromSP(Context context,String fileName,String key,Class clazz){
		Object obj = null;
		SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		if(clazz.getName().equals(String.class.getName())){
			obj = sharedPreferences.getString(key, "");
		}else if(clazz.getName().equals(Integer.class.getName())){
			obj = sharedPreferences.getInt(key, 0);
		}else if(clazz.getName().equals(Boolean.class.getName())){
			obj = sharedPreferences.getBoolean(key, false);
		}else if(clazz.getName().equals(Long.class.getName())){
			obj = sharedPreferences.getLong(key, 0);
		}else if(clazz.getName().equals(Float.class.getName())){
			obj = sharedPreferences.getFloat(key, 0);
		}
		return obj;
	}
	
//	public static <T> T getValueFromSP(Context context,String fileName,String key){
//		T t=null;
//		return null;
//	}
	
}
