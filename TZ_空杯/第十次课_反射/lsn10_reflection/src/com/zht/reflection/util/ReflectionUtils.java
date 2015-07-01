/**
 * Project Name:lsn10_reflection
 * File Name:ReflectionUtils.java
 * Package Name:com.zht.reflection.util
 * Date:2015-6-24下午4:14:24
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
 * Date: 2015-6-24 下午4:14:24 <br/>
 * 
 * @author acer
 * @version
 * @since JDK 1.6
 * @see
 */
public class ReflectionUtils {

	public static void initViews(Object activity) {
		// 通过对象获得类模板
		Class cls = activity.getClass();
		// 获得我们在activity类中定义的一系列属性
		Field[] fields = cls.getDeclaredFields();
		// 循环属性
		for (Field field : fields) {
			// 只有控件才赋值，因此这里要判断
			// 获得属性的数据类型模板
			Class dataType = field.getType();
			// 因为activity里面可能定义各种各样的类型，而我只想对控件进行赋值
			if (View.class.isAssignableFrom(dataType)) {// View.class这个类型是否可以从dataType转化过来
				// 自动利用反射技术，将所有的控件属性与对应ID的控件进行匹配赋值
				// 因为findViewById不在我们自己定义的activity中定义的，而是从父类继承下来的，因此要使用getMethod,而不是getDeclareMethod
				try {
					Method method = cls.getMethod("findViewById", new Class[]{ int.class });//参数类型
					//第一个参数：那个对象来调用;第二个参数：要传入具体的对应控件的ID的值
					Class idCls = R.id.class;
					//又继续拿属性
					//因为我们设计Activity中的属性名是特地与他对应的ID保持一致，因此field.getName()的返回就是对应的ID的名字
					Field idField = idCls.getDeclaredField(field.getName());//ID和控件名保持一致的用意
					//第一：属性是public类型的，所以无需设置accessable，第二：字段是静态的，则可以忽略掉--随便给一个对象,但是不能给null
					//这里已经获得了ID的int型的值了
					Object args = idField.get(idField);
					//这里已经调用了findViewById方法
					Object value = method.invoke(activity, new Object[]{args});
					field.setAccessible(true);
					//这里是将findViewById方法的返回赋值到对应的属性
					field.set(activity, value);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
