package com.zoujm.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.view.View;

public class ReflectionUtil {


	
	/**
	 * 初始化控件
	 * 属性名与id保持一致
	 *@author 邹继明
	 *@date 2015-6-5下午9:59:57
	 *@param obj 
	 *@param res R文件
	 *@return void
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void initView(Object obj,Class res) {
		Class clazz = obj.getClass();
		try {
			Field[] fields = clazz.getDeclaredFields();
			Method method = clazz.getMethod("findViewById", new Class[]{int.class});
			for (Field field : fields) {
				field.setAccessible(true);
				Class dataType = field.getType();
				//判断是否可以从dataType转成View
				if(View.class.isAssignableFrom(dataType)){
					//获取R.id 类中的字段
					Field idField = res.getDeclaredField(field.getName());
					//如果idField是静态的 则idField.get(obj)中obj将被忽略掉
					//获取id的值
					Object args = idField.get(idField);
					//调用findViewById
					Object value = method.invoke(obj, new Object[]{args});
					field.set(obj, value);
				}
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}

	}

}
