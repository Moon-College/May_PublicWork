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
			//判断field的类型是否可以转成View类型
			if(View.class.isAssignableFrom(type)){
				field.setAccessible(true);
				 try {
					//调用activity里面的父类方法findViewById
					Method method = cls.getMethod("findViewById", new Class[]{int.class});
					Class idCls = R.id.class;
					Field rfield = idCls.getDeclaredField(field.getName());
					//获得R.id类里面的指定名字field的值，因为是静态的值所以get参数可以随意
					Object rId = rfield.get(activity);
					//具体调用findViewById方法
					Object value = method.invoke(activity, new Object[]{rId});
					//设置activity类中自己定义的控件变量的值为invoke的结果
					field.set(activity, value);
				} catch (Exception e) {
					e.printStackTrace();
				}
				 
			}
		}
		
	}
	
}
