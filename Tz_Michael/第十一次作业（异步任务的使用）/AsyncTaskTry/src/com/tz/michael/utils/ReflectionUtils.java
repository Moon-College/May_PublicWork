package com.tz.michael.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.tz.michael.activity.R;

import android.app.Activity;
import android.view.View;

public class ReflectionUtils {

	/**
	 * 使用的前提是xml中的id和activity中的控件名字一样
	 * @param activity
	 */
	public static void findViews(Activity activity) {
		//拿到模具
		Class clazz=activity.getClass();
		//拿到所有的属性
		Field[] fields=clazz.getDeclaredFields();
		for(Field field:fields){
			//拿到域的类型
			Class type=field.getType();
			//判断(所有控件都是View的子类)
			if(View.class.isAssignableFrom(type)){
				try {
					//拿取R.id.value
					Class idCls=R.id.class;
					Field idField=idCls.getField(field.getName());
					Object value=idField.get("");
					//拿到findViewById方法
					Method method=clazz.getMethod("findViewById", int.class);
					Object control=method.invoke(activity, value);
					//因为是私有属性，所以这里需要申明拿取更多权限
					field.setAccessible(true);
					field.set(activity, control);
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	
	
}
