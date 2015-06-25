package com.junwen.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.view.View;

import com.junwen.annotation.IocAnnotation;

public class IocForAnnotation {

	public static void FindViewByID(Object activity){
		//��ȡ��ǰActivity��
		Class   class1 = activity.getClass();
		//��ȡactivity�µ���������
		Field[] fields = class1.getDeclaredFields();
		//������������
		for (Field field : fields) {
			IocAnnotation annotation = field.getAnnotation(IocAnnotation.class);
			int value = annotation.value();
			if(value>0){
				//ִ��findviewbyid����ÿؼ�
				//ֻ����ؼ�����
				if(View.class.isAssignableFrom(field.getType())) {
					//����ǿؼ�
					//ִ��findviewbyid����ÿؼ�
					try {
						//���findviewbyid����
						Method method = class1.getMethod("findViewById", int.class);
						//���ע���ϵ�id
						Object invoke = method.invoke(activity, value);
						//��������Լ���Ȩ��
						field.setAccessible(true);
						//��������Ը�ֵ��ȥ
						System.out.println("ע�⣺"+value);
						System.out.println("FindViewById"+invoke);
						field.set(activity, invoke);
					} catch (Exception e) {
					}
				}
			}
		}
	}
}
