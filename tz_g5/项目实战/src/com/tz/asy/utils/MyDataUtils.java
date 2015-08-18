package com.tz.asy.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.tz.asy.common.MyConstants;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class MyDataUtils {
	
	/**
	 * sharedpreference
	 * @param context
	 * @param fileName
	 */
	public static void putData(Context context,String fileName,Map<String,Object> map){
		SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		Editor edit = sharedPreferences.edit();
		for(Entry<String,Object> set :map.entrySet()){
			String key = set.getKey();
			Object value = set.getValue();
			if(value instanceof String){
				edit.putString(key, (String)value);
			}else if(value instanceof Boolean){
				edit.putBoolean(key, (Boolean)value);
			}else if(value instanceof Float){
				edit.putFloat(key, (Float)value);
			}else if(value instanceof Long){
				edit.putLong(key, (Long)value);
			}else{
				edit.putInt(key, (Integer)value);
			}
		}
		edit.commit();
	}
	
	
	/**
	 * ��ȡsharedpreference
	 */
	public static Object getData(Context context,String fileName,String key,Class clazz){
		SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		if(clazz.getName().equals(String.class.getName())){
			return sharedPreferences.getString(key, "");
		}else if(clazz.getName().equals(Integer.class.getName())){
			return sharedPreferences.getInt(key, 0);
		}else if(clazz.getName().equals(Float.class.getName())){
			return sharedPreferences.getFloat(key, 0);
		}else if(clazz.getName().equals(Long.class.getName())){
			return sharedPreferences.getLong(key, 0);
		}else{
			return sharedPreferences.getBoolean(key, false);
		}
	}
	
	/**
	 * 保存cookie
	 * @param context
	 * @param headers
	 */
	public static void savaHead(Context context,Header[] headers){
		for(Header header : headers){
			if(header.getName().equals(MyConstants.SETCOOKIE)){
				//保存cookieͷ
				Map<String,Object> map = new HashMap<String,Object>();
				map.put(header.getName(), header.getValue());
				MyDataUtils.putData(context, MyConstants.COOKIE, map);
			}
		}
	}
	
	/**
	 * ��ȡcookie
	 */
	public static NameValuePair getHeader(Context context){
		Object value = getData(context, MyConstants.COOKIE, MyConstants.SETCOOKIE, String.class);
		return new BasicNameValuePair(MyConstants.SETCOOKIE, (String)value);
	}
}
