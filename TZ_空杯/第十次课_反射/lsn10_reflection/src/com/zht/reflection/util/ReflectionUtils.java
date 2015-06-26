/**
 * Project Name:lsn10_reflection
 * File Name:ReflectionUtils.java
 * Package Name:com.zht.reflection.util
 * Date:2015-6-24����4:14:24
 * Copyright (c) 2015, shixi_hongtao@staff.sina.com.cn All Rights Reserved.
 *
 */

package com.zht.reflection.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.zht.reflection.R;

import android.app.Activity;
import android.view.View;

/**
 * ClassName:ReflectionUtils <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-6-24 ����4:14:24 <br/>
 * 
 * @author acer
 * @version
 * @since JDK 1.6
 * @see
 */
public class ReflectionUtils {

	public static void initViews(Object activity) {
		// ͨ����������ģ��
		Class cls = activity.getClass();
		// ���������activity���ж����һϵ������
		Field[] fields = cls.getDeclaredFields();
		// ѭ������
		for (Field field : fields) {
			// ֻ�пؼ��Ÿ�ֵ���������Ҫ�ж�
			// ������Ե���������ģ��
			Class dataType = field.getType();
			// ��Ϊactivity������ܶ�����ָ��������ͣ�����ֻ��Կؼ����и�ֵ
			if (View.class.isAssignableFrom(dataType)) {// View.class��������Ƿ���Դ�dataTypeת������
				// �Զ����÷��似���������еĿؼ��������ӦID�Ŀؼ�����ƥ�丳ֵ
				// ��ΪfindViewById���������Լ������activity�ж���ģ����ǴӸ���̳������ģ����Ҫʹ��getMethod,������getDeclareMethod
				try {
					Method method = cls.getMethod("findViewById", new Class[]{ int.class });//��������
					//��һ���������Ǹ�����������;�ڶ���������Ҫ�������Ķ�Ӧ�ؼ���ID��ֵ
					Class idCls = R.id.class;
					//�ּ���������
					//��Ϊ�������Activity�е����������ص�������Ӧ��ID����һ�£����field.getName()�ķ��ؾ��Ƕ�Ӧ��ID������
					Field idField = idCls.getDeclaredField(field.getName());//ID�Ϳؼ�������һ�µ�����
					//��һ��������public���͵ģ�������������accessable���ڶ����ֶ��Ǿ�̬�ģ�����Ժ��Ե�--����һ������,���ǲ��ܸ�null
					//�����Ѿ������ID��int�͵�ֵ��
					Object args = idField.get(idField);
					//�����Ѿ�������findViewById����
					Object value = method.invoke(activity, new Object[]{args});
					field.setAccessible(true);
					//�����ǽ�findViewById�����ķ��ظ�ֵ����Ӧ������
					field.set(activity, value);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
