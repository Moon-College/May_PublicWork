package com.tz.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.app.Activity;
import android.view.View;

public class reflectionUtil {

	public static void reflectionUI(Activity activity) {
		//当前activity
		Class classes = activity.getClass();
		//获取activity的所有属性
		Field[] fileids = classes.getDeclaredFields();
		//便利所有属性
		for (Field field : fileids) {
			IocAnnotation annotation = field.getAnnotation(IocAnnotation.class);
			int value = annotation.value();
			if (value > 0) {

				boolean ter = View.class.isAssignableFrom(field.getType());
				if (ter) {
					try {
						//获得findviewById方法
						Method method = classes.getMethod("findViewById",
								int.class);
						//获得id
						Object invoke = method.invoke(activity, value);
						//给这个属性加权限
						field.setAccessible(true);
						//属性赋值
						field.set(activity, invoke);
											
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
		}
	}
}
