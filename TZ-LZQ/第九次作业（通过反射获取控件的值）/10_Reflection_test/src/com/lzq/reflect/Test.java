package com.lzq.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

//	上面没有导入Student  ，说明  Test 和 Student没有耦合
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 实例化一个对象
		// --> 开辟内存 JVM 开辟的
		// Student s = new Student();

		try {
			// 并没有跟我们的Student对象进行耦合
			Class cls = Class.forName("com.lzq.reflect.bean.Student");
			// 类自己定义的属性列表
			// Field[] declaredFields = cls.getDeclaredFields();
			//
			// for (Field f : declaredFields) {
			// System.out.println(f.getName());
			// }

			/**
			 * 方法一：
			 */
			/*Field field = cls.getDeclaredField("name");
			// 这是根据类模版 进行实例化（正在调用默认的构造方法 ）
			Object obj = cls.newInstance();
			// 强行破解 获取private
			field.setAccessible(true);
			// 告诉我具体要获得那一个对象的属性
			// 这就是获得对应属性的值 参数是具体你要去获得哪一个对象 返回的就是这个属性的值
			String value = (String) field.get(obj);
			*/

			/**
			 * 方法二：
			 */
			Object obj = cls.newInstance();
			// 获得个Name这个方法对象
			Method method = cls.getDeclaredMethod("getName", new Class[]{});
			// 进行调用
			Object value2 = method.invoke(obj, null);
			
			System.out.println(value2);
			// 类继承的属性的列表
			// Field[] fields = cls.getFields();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
