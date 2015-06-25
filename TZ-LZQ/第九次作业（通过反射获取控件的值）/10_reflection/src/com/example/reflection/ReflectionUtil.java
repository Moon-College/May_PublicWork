package com.example.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.util.Log;
import android.view.View;

public class ReflectionUtil {

	public static void initViews(Object activity) {
		// ͨ�����������ģ��
		Class cls = activity.getClass();
		// ����������Լ���activity���ж����һЩ������
		Field[] fields = cls.getDeclaredFields();
		// ѭ������
		for (Field field : fields) {
			// ����������Ե���������ģ��
			Class type = field.getType();
			// ��Ϊacitivity������ܶ�����ָ���������
			// ������ֻ�� �Կؼ����и�ֵ
			// View.class�Ƿ���Դ�type����ת������ --> View view = tv_one;
			// �������������Լ���� ����ֻ�Կؼ����д���
			if (View.class.isAssignableFrom(type)) {
				// Log.i("sss", type.getName());
				// ��Ϊ������activity���涨������������˽�еģ�����Ҫ�����ƻ���װ
				field.setAccessible(true);
				// ���ǽ����Զ����÷��似�������еĿؼ��������Ӧ����ID�Ŀؼ�����ƥ�丳ֵ
				try {
					// ������������Ҫ���÷���������ǵ�findViewById����
					// ��ΪfindViewById����������������Զ������activity���涨�� �ǴӸ���̳�������
					// ����Ҫʹ��getMethod
					Method method = cls.getMethod("findViewById",
							new Class[] { int.class });
					// ��÷����д���ĵڶ���������ֵ ���ID ����R.id������� �ڶ�����֪������Ķ�Ӧ����������
					Class id_cls = R.id.class;
					// ��Ϊ���ǵ����Activity�е��������� �ص�������ID����һ��
					// ����field.geName()�ķ��ؾ��Ƕ�ӦID������
					Field id_field = id_cls.getDeclaredField(field.getName());
					// ��һ��Ϊ���������public ������������setAccessible �ڶ�������������Ǿ�̬��
					// ���Դ���Ĳ�������� ������㴫��OK
					// �����Ѿ������ID��ֵ
					Object id_value = id_field.get(id_field);
					// ��Ҫ����������� ��һ��������ָ ��һ������������ �ڶ������� Ҫ�������Ķ���ؼ���ID��ֵ
					// �����Ѿ�������findViewById����
					Object value = method.invoke(activity, id_value);
					// �����ǽ�findViewById�����ķ���ֵ ��ֵ����Ӧ������
					field.set(activity, value);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		// this.findViewById(R.id.tv_one)
	}
}
