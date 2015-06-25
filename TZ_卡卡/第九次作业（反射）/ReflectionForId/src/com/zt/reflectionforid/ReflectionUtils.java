package com.zt.reflectionforid;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.app.Activity;
import android.view.View;

public class ReflectionUtils {

	public static void initViews(Activity activity) {
		Class<? extends Activity> class1 = activity.getClass();
		// �����������������
		Field[] fields = class1.getDeclaredFields();
		for (Field field : fields) {
			// ����������Ե�����
			Class type = field.getType();
			// type �Ƿ� ��view���͵�
			if (View.class.isAssignableFrom(type)) {

				// ���÷��似�� findViewById
				Object value = null;
				try {
					Method method = class1.getMethod("findViewById",
							new Class[] { int.class });
					//�õ�R�ļ���
					Class idcls=R.id.class;
					//�������ֵõ�������id
					Field idField = idcls.getDeclaredField(field.getName());
					Object id = idField.get(idField);
					//��������id����һ�� ִ��findViewById 
					Object invoke = method.invoke(activity, new Object[]{id});
					field.setAccessible(true);
					field.set(activity, invoke);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			// System.out.println(type);
		}

	};
}
