package com.dd.downloadphoto;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.util.Log;
import android.view.View;

public class Reflection {

	public static void findView(Object activity){
		Class class1 = activity.getClass();
		Field[] declaredFields = class1.getDeclaredFields();
		for (Field field : declaredFields) {
			Log.v("home", field.getName());
			field.setAccessible(true);
			if (View.class.isAssignableFrom(field.getType())) {
				Class cls = R.id.class;
				try {
					Method declaredMethod = class1.getMethod("findViewById", new Class[]{int.class});
					Field declaredField = cls.getDeclaredField(field.getName());
					Object object = declaredField.get(declaredField);
					Object value = declaredMethod.invoke(activity, new Object[]{object});
					field.set(activity, value);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
