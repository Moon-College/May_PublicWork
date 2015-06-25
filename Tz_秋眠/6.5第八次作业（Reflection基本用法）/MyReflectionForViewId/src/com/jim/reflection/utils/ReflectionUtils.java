package com.jim.reflection.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.jim.reflection.R;

import android.app.Activity;
import android.view.View;

public class ReflectionUtils {
	/**
	 * �÷���дfindViewById������ ע��:ʹ�ô˷����ؼ�idҪ�������涨��Ķ�������һ��
	 * 
	 * @param activity
	 */
	public static void initViews(Activity activity) {
		// ͨ�����ݹ�����activity�����������ڵ���(����ָActivity)
		Class cls = activity.getClass();
		// ͨ����õ���ȡ���ڸ����ж��������
		Field[] fields = cls.getDeclaredFields();
		// �������������е�����
		for (Field field : fields) {
			// ���ÿ��Ի�����װ(private����)
			field.setAccessible(true);
			// ��ȡ��ǰ���Ե����ͣ���View�࣬long��ȵ�
			Class dataType = field.getType();
			// �жϸ����Ƿ����ת��ΪView�࣬Ҳ����View������
			if (View.class.isAssignableFrom(dataType)) {
				try {
					// ͨ��findViewById�ַ����ҵ�findViewById����
					Method method = cls.getMethod("findViewById",// ������
							new Class[] { int.class });// ���ݽ���÷���������
					// ��ȡR.id�࣬Ϊ��ȡ�ؼ���id
					Class idCls = R.id.class;
					// ͨ����������Ӧ�Ķ������ƻ��R.id��������Ӧ���ƵĶ���
					Field idField = idCls.getDeclaredField(field.getName());
					// ��ȡR.id���е�ֵ��������ΪR�ļ��е��඼��static��̬�ģ�
					// ������㴫��ֵ���ᱻ���ԣ�һ�㴫��Object����
					Object args = idField.get(idField);
					// ʹ��findViewById����
					Object value = method.invoke(activity,
							new Object[] { args });
					// ���÷���ֵ��activity��
					field.set(activity, value);
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
