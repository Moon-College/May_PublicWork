package com.qfx.reflectionfindview.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.qfx.reflectionfindview.R;


import android.util.Log;
import android.view.View;

public class ReflectionUtil {

	public static final String TAG = "REFLECTION";
	
	public static void initViews(Object activity) {
		//通过对象获得类模板
		Class clazz = activity.getClass();
		//获得了我们自己在activity里面定义的一系列属性
		//获取自身所在类定义的所有属性
		//clazz.getFields() 是获取父类里面定义的属性
		Field[] fields = clazz.getDeclaredFields();
		//循环属性
		for (Field field : fields) {
			//获取属性的类型模板
			Class dataType = field.getType();
			//因为activity里面可能有各种各样的属性，而我们只需要对控件进行赋值
			//View.class是否可以成dataType转化而来，所以这个条件就约束了只对空间处理
			if (View.class.isAssignableFrom(dataType)) {
				//因为在activity里面定义的属性是私有的，所以我们要破坏封装
				field.setAccessible(true);
				Log.i(TAG, dataType.getName());
				//利用反射技术将所有的控件属性与对应设置id的控件进行匹配赋值
				try {
					//利用反射调用findViewById方法
					//因为findViewById方法不再我们自定义的activity里面，是从父类继承下来的，所以这里要用getMethod而不是getDeclaredMethod
					Method method = clazz.getMethod("findViewById", new Class[]{int.class});
					//获得方法中传入的第二个参数值，这个id放在R.id这个类里面，并且我们知道对应的属性名字
					Class idCls = R.id.class;
					//如果activity中的属性名特地与对应的id保持一致，那么field.getName()返回的就是对应的ID名字
					Log.i(TAG, field.getName());
					Field idField = idCls.getDeclaredField(field.getName());//获取属性的名称
					//第一，由于这个是public 所以无需设置access(破坏封装).第二， 由于这个属性是静态的，所以传入的参数会被忽略，所以随便穿都OK
					Object args = idField.get(idField);//获取属性的值
					//我要调用这个方法 第一个参数是指 哪个对象来调用 第二个参数要传入具体的对象控件的ID的值
					//这里已经调用了findViewById方法
					Object value = method.invoke(activity, new Object[]{args});
					//这里是将findViewById方法的返回值 赋值到对应的属性
					field.set(activity, value);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
