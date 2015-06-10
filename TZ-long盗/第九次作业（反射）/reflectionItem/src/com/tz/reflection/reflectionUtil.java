package com.tz.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.app.Activity;
import android.view.View;

public class reflectionUtil {

	public static void reflectionUI(Activity activity) {
		//��ǰactivity
		Class classes = activity.getClass();
		//��ȡactivity����������
		Field[] fileids = classes.getDeclaredFields();
		//������������
		for (Field field : fileids) {
			IocAnnotation annotation = field.getAnnotation(IocAnnotation.class);
			int value = annotation.value();
			if (value > 0) {

				boolean ter = View.class.isAssignableFrom(field.getType());
				if (ter) {
					try {
						//���findviewById����
						Method method = classes.getMethod("findViewById",
								int.class);
						//���id
						Object invoke = method.invoke(activity, value);
						//��������Լ�Ȩ��
						field.setAccessible(true);
						//���Ը�ֵ
						field.set(activity, invoke);
											
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
		}
	}
}
