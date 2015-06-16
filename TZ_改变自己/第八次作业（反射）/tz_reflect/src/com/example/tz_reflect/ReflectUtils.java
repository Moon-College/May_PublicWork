package com.example.tz_reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.util.Log;
import android.view.View;

public class ReflectUtils {
	public static void initViews(Activity activity) {
		//获得类模板
		Class cls = activity.getClass();
		//获得了我们自己在activity类中定义的一系列属性
		Field[] fields = cls.getDeclaredFields();
		//循环属性
		for(Field field : fields) {
			//获取属性的数据类型模板
			Class dataType = field.getType();
			Log.e("wdj","dataType = " + dataType);
			//因为activity里面可能定义各种各样的类型
			//而我们只想对控件进行赋值
			if(View.class.isAssignableFrom(dataType)) {
				//我们会利用发射技术讲所有的控件与对应设置id的控件进行匹配赋值
				//因为findViewById方法不在我们activity里面定义，所以我们要用getMethod
				field.setAccessible(true);
				try {
					Method method = cls.getMethod("findViewById", new Class[]{int.class});
					//我要调用的第一个参数指哪个对象来调用，第二个参数要传入具体对象控件的ID的值
					Class idCls = R.id.class;
					//因为我们设计activity中的属性名师特地与他对饮的ID保持一致
					Field idField = idCls.getDeclaredField(field.getName());
					//由于这个属性是public的，所以无需设置access，第二鱿鱼这个属性是静态的，所以传入的参数讲会被忽略
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
