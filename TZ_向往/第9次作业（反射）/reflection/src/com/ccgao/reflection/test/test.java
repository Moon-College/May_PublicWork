package com.ccgao.reflection.test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class test {

	public static void main(String[] args) {
		try {
			// System.out.println("hello");
			Class cls = Class.forName("com.ccgao.reflection.bean.Student");
			Object obj = cls.newInstance(); // 将传入的类名实例化
			// 类本身的属性
			Field[] myFileds = cls.getDeclaredFields();
			for (Field field : myFileds) {
				 //System.out.println(field.getName());
				 /*Field myField = null;
				try {
					myField = cls.getDeclaredField(field.getName());
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				}
				myField.setAccessible(true);
				Object value = myField.get(obj);
				System.out.println(value);*/
				 field.setAccessible(true);
				 Object value = field.get(obj);
				 //System.out.println(value);
			}
			
			/*Field field = null;
			try {
				field = cls.getDeclaredField("name");
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			field.setAccessible(true);
			Object value = field.get(obj);
			System.out.println(value);*/
			
			//获取getName这个方法
			Method method = cls.getDeclaredMethod("getName", new Class[]{});
			//进行调用
			Object value = method.invoke(obj, null);
			System.out.println(value);
			// 类继承的属性
			// Field[] myFileds1 = cls.getFields();
			// for(Field field:myFileds1){
			// System.out.println(field.getName());
			// }
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
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
