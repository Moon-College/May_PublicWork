package com.zoujm.utils;

import java.lang.reflect.Field;

import android.app.Activity;
import android.util.Log;

import com.zoujm.inject.InjectView;

public class AutoInjectUtil {
	public final static String TAG = AutoInjectUtil.class.getSimpleName().toString();
	
	/**
	 * 自动注入所有视图
	 * @author Json
	 * */
	public static void autoInjectAllView(Activity activity) throws IllegalAccessException, IllegalArgumentException{
		
		//得到Activity对应的Class
	    Class clazz=activity.getClass();
	    //得到该Activity的所有字段
		Field[] fields = clazz.getDeclaredFields();
		Log.v(TAG, "fields size -->"+ fields.length);

		//判断字段是否被标注InjectView
		for(Field field : fields){
			 //判断字段是否标注InjectView
			if(field.isAnnotationPresent(InjectView.class)){
				//获得注解的实例
				InjectView inject = field.getAnnotation(InjectView.class);
				//获取id
				int id = inject.id();
				if(id>0){
					//反射访问私有成员，必须加上这句
					field.setAccessible(true);
					//然后对这个属性赋值
					field.set(activity, activity.findViewById(id));
				}
			}
			
		}
		
	}

}
