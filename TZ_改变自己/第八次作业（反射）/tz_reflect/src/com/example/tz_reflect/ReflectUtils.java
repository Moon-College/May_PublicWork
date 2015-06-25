package com.example.tz_reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.util.Log;
import android.view.View;

public class ReflectUtils {
	public static void initViews(Activity activity) {
		//�����ģ��
		Class cls = activity.getClass();
		//����������Լ���activity���ж����һϵ������
		Field[] fields = cls.getDeclaredFields();
		//ѭ������
		for(Field field : fields) {
			//��ȡ���Ե���������ģ��
			Class dataType = field.getType();
			Log.e("wdj","dataType = " + dataType);
			//��Ϊactivity������ܶ�����ָ���������
			//������ֻ��Կؼ����и�ֵ
			if(View.class.isAssignableFrom(dataType)) {
				//���ǻ����÷��似�������еĿؼ����Ӧ����id�Ŀؼ�����ƥ�丳ֵ
				//��ΪfindViewById������������activity���涨�壬��������Ҫ��getMethod
				field.setAccessible(true);
				try {
					Method method = cls.getMethod("findViewById", new Class[]{int.class});
					//��Ҫ���õĵ�һ������ָ�ĸ����������ã��ڶ�������Ҫ����������ؼ���ID��ֵ
					Class idCls = R.id.class;
					//��Ϊ�������activity�е�������ʦ�ص�����������ID����һ��
					Field idField = idCls.getDeclaredField(field.getName());
					//�������������public�ģ�������������access���ڶ�������������Ǿ�̬�ģ����Դ���Ĳ������ᱻ����
					Object args = idField.get(idField);
					Object value = method.invoke(activity, new Object[]{args});
					
					field.set(activity, value);
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
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
