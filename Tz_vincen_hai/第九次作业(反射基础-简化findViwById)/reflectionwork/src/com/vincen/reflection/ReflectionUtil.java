package com.vincen.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.view.View;

public class ReflectionUtil {

	public static void initView(Activity activity) {
		Class<? extends Activity> cls = activity.getClass();
		// ��ȡactivity�����������
		try {
			Field[] fields = cls.getDeclaredFields();
			Method method = cls.getMethod("findViewById",
					new Class[] { int.class });
			for (Field field : fields) {
				// ��ȡ���Ե�����
				Class type = field.getType();
				// �ж�type�Ƿ����ת����View
				if (View.class.isAssignableFrom(type)) {
					Class idCls = R.id.class;
					// ������������ȡid
					Field idField = idCls.getDeclaredField(field.getName());
					
					Object args = idField.get(idField);
					//��ʼ����ȡ��Ӧ�Ŀؼ�  �൱��findViewById
					Object value = method.invoke(activity, new Object[]{args});
					//��MainActivity���ж����������˽�еģ����������´����ڷ�װ
					field.setAccessible(true);
					field.set(activity, value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
