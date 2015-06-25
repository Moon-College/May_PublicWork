package com.knight.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflctionTest {
	//反射机制
	public static void main (String[] args) {
		getInstance("com.knight.reflection.Student");
	}
	public static Object getInstance(String className){
		try {
			//拿到类的模板
			Class cls = Class.forName(className);
			//根据类模板进行实例化(调用了默认构造方法)
			Object obj = cls.newInstance();
			//获得类自己的属性列表
			Field[] fields = cls.getDeclaredFields();
			//获得类继承下来的属性列表
//			Field[] fields2 = cls.getFields();
//			for (Field field : fields){
//				field.setAccessible(true);
//				Object value = field.get(obj);
//				System.out.println(value);
//				
//			}
			//得到getName这个方法对象
			Method method = cls.getDeclaredMethod("getName", new Class[]{});
			Object invoke = method.invoke(obj, null);
			System.out.println(invoke);
//			Field field = cls.getDeclaredField("name");
			//反射暴力破解封装
			//获得对应属性的值参数是具体你要获取的对象 返回就是这个属性的值
		} catch (Exception e) {
			e.printStackTrace();
		}
		return className;
	}
}
