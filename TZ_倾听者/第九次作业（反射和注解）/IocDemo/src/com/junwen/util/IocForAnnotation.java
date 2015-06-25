package com.junwen.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.view.View;

import com.junwen.annotation.IocAnnotation;

public class IocForAnnotation {

	public static void FindViewByID(Object activity){
		//获取当前Activity类
		Class   class1 = activity.getClass();
		//获取activity下的所有属性
		Field[] fields = class1.getDeclaredFields();
		//遍历所有属性
		for (Field field : fields) {
			IocAnnotation annotation = field.getAnnotation(IocAnnotation.class);
			int value = annotation.value();
			if(value>0){
				//执行findviewbyid，获得控件
				//只处理控件属性
				if(View.class.isAssignableFrom(field.getType())) {
					//如果是控件
					//执行findviewbyid，获得控件
					try {
						//获得findviewbyid方法
						Method method = class1.getMethod("findViewById", int.class);
						//获得注释上的id
						Object invoke = method.invoke(activity, value);
						//给这个属性加上权限
						field.setAccessible(true);
						//给这个属性赋值上去
						System.out.println("注解："+value);
						System.out.println("FindViewById"+invoke);
						field.set(activity, invoke);
					} catch (Exception e) {
					}
				}
			}
		}
	}
}
