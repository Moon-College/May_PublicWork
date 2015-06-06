package com.dd.sixfourhomework;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.view.View;

public class Reflection {

	public static void findView(Object activity) throws Exception{
		Class class1 = activity.getClass();
		Field[] declaredFields = class1.getDeclaredFields();
		for (Field field : declaredFields) {
			field.setAccessible(true);
			if (View.class.isAssignableFrom(field.getType())) {
				Method method = class1.getMethod("findViewById", new Class[]{int.class});
				Class cls = R.id.class;
				Field declaredField = cls.getDeclaredField(field.getName());
				Object object = declaredField.get(declaredField);
				Object invoke = method.invoke(activity, object);
				field.set(activity, invoke);
			}
		}
	}
}
