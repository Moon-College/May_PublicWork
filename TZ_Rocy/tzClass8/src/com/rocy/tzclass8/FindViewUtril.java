package com.rocy.tzclass8;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.view.View;

public class FindViewUtril {
    
	public static void getView(Object activity){
		Class activityClass = activity.getClass();
		Field[] activityFields = activityClass.getDeclaredFields();
		//获得每个参数
		for (Field activityField : activityFields) {
			//如果当前参数对象来自View类
			if (View.class.isAssignableFrom(activityField.getType())) {
				try {
					Class rClass=R.id.class;
					Field rField = rClass.getDeclaredField(activityField.getName());
					//活的给赋值对象赋值的方法 
					Method method = activityClass.getMethod("findViewById", new Class[]{int.class});
					//给当前类赋值
					Object value = method.invoke(activity, rField.get(1));
					activityField.setAccessible(true);
					activityField.set(activity, value);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
