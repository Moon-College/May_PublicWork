package com.jim.mydownload.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.view.View;

public class ReflectionUtils {
	/**
	 * �÷���дfindViewById������ ע��:ʹ�ô˷����ؼ�idҪ�������涨��Ķ�������һ��
	 * 
	 * @param activity
	 *            ����Ҫ�������activity
	 * @param c
	 *            ���ݵ�ǰ��Ӧ�õ�R�ļ���
	 */
	public static void initViews(Activity activity, Class R) {
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
					Class idCls = R;
					// ͨ����������Ӧ�Ķ������ƻ��R.id��������Ӧ���ƵĶ���,���ԣ��ؼ�idҪ�������涨��Ķ�������һ��
					Field idField = idCls.getDeclaredField(field.getName());
					// ��ȡR.id���еĶ�Ӧ��intֵ�������args���棬������ΪR�ļ��е��඼��static��̬�ģ�
					// ������㴫��ֵ���ᱻ���ԣ�һ�㴫��Object����
					Object args = idField.get(idField);
					// ʹ��findViewById����
					// ��ʱ��method����findViewById�������ڴ����activity����ʹ�ã�����Ϊargs
					Object value = method.invoke(activity,
							new Object[] { args });
					// ���÷���ֵ��activity�У�value�൱��R.id.***;
					// �൱��ʹ���ˣ���TextView tv1 =
					// (TextView)activity.findViewById(R.id.tv1);
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
