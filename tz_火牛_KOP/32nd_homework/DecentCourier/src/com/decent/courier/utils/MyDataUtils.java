package com.decent.courier.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
			if(value == null){
				DecentLogUtil.d("into putData must continue with key="+key+" and edit="+value);
				continue;
			}
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
	public static <T> T getData(Context context,String fileName,String key,Class T){
		SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		T result;
		if(String.class.isAssignableFrom(T)){
			result = (T) sharedPreferences.getString(key, "");
		}else if(Integer.class.isAssignableFrom(T)){
			result = (T) Integer.valueOf(sharedPreferences.getInt(key, 0));
		}else if(Float.class.isAssignableFrom(T)){
			result = (T) Float.valueOf(sharedPreferences.getFloat(key, 0));
		}else if(Long.class.isAssignableFrom(T)){
			result = (T)Long.valueOf(sharedPreferences.getLong(key, 0));
		}else{
			result = (T)Boolean.valueOf(sharedPreferences.getBoolean(key, false));
		}
		return result;
	}
	
	/**
	 * 保存cookie
	 * @param context
	 * @param headers
	 */
	public static void savaHead(Context context,Header[] headers){
		for(Header header : headers){
			if(header.getName().equals(DecentConstants.SETCOOKIE)){
				//保存cookieͷ
				Map<String,Object> map = new HashMap<String,Object>();
				map.put(header.getName(), header.getValue());
				MyDataUtils.putData(context, DecentConstants.COOKIE, map);
			}
		}
	}
	
	/**
	 * ��ȡcookie
	 */
	public static NameValuePair getHeader(Context context){
		Object value = getData(context, DecentConstants.COOKIE, DecentConstants.SETCOOKIE, String.class);
		return new BasicNameValuePair(DecentConstants.SETCOOKIE, (String)value);
	}
}
