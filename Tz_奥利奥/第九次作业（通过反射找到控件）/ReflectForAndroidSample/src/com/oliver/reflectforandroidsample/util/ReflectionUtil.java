package com.oliver.reflectforandroidsample.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.oliver.reflectforandroidsample.R;

import android.view.View;

/**
 * ���乤����
 * @author Oliver
 *
 */
public class ReflectionUtil {

	/**
	 * ͨ���Զ���ķ���Զ�������Խ��г�ʼ��
	 * @param obj	��Ҫ��ʼ���Ķ���
	 */
	public static final void initView(Object obj){
		//�õ����������������
		Class<?> objClass = obj.getClass();
		Field[] fields = objClass.getDeclaredFields();
		//ѭ����ȡ�����ԣ��Է��ϳ�ʼ�������͵����Խ��г�ʼ��
		for (Field field : fields) {
			//�õ�ÿ�����Ե���������
			Class<?> dataType = field.getType();
			//�ж����������Ƿ����ת����View
			if(View.class.isAssignableFrom(dataType)){
				//�õ��������Գ�ʼ���ķ���
				try {
					Method initMethod = objClass.getMethod("findViewById", new Class[]{int.class});
					//�õ����÷�������Ҫ����Ĳ���
					//��ȡR�ļ��е�id��
					Class<?> idClass = R.id.class;
					//���ݴ���������������õ���R�ļ���Ӧid���е�Id
					Field idField = idClass.getDeclaredField(field.getName());
					//����findViewById����
					//����һΪ�����Ĺ����ߣ�������Ϊ���ø÷�������Ҫ����Ĳ���
					Object initObj = initMethod.invoke(obj, new Object[]{idField.get(idClass)});
					//��ֵ����Ӧ����
					field.setAccessible(true);
					field.set(obj, initObj);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
