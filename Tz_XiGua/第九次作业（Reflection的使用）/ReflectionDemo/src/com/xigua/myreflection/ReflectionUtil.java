package com.xigua.myreflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.app.Activity;
import android.view.View;

public class ReflectionUtil {

	public static void initViews(Activity activity){
		
		Class cls = activity.getClass();
		Field[] fields = cls.getDeclaredFields();
		for(Field field : fields){
			Class type = field.getType();
			//�ж�field�������Ƿ����ת��View����
			if(View.class.isAssignableFrom(type)){
				field.setAccessible(true);
				 try {
					//����activity����ĸ��෽��findViewById
					Method method = cls.getMethod("findViewById", new Class[]{int.class});
					Class idCls = R.id.class;
					Field rfield = idCls.getDeclaredField(field.getName());
					//���R.id�������ָ������field��ֵ����Ϊ�Ǿ�̬��ֵ����get������������
					Object rId = rfield.get(activity);
					//�������findViewById����
					Object value = method.invoke(activity, new Object[]{rId});
					//����activity�����Լ�����Ŀؼ�������ֵΪinvoke�Ľ��
					field.set(activity, value);
				} catch (Exception e) {
					e.printStackTrace();
				}
				 
			}
		}
		
	}
	
}
