package com.myandroid.takingpictures.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.myandroid.takingpictures.R;


import android.app.Activity;
import android.view.View;

public class ReflectionUtil {
	
	public static void initViews(Object activity)
	{
		//通过activity对象获取类模版
		Class cls= activity.getClass();
		//获得了我们自己在activity类中定义的一系列属性
		Field[] fields= cls.getDeclaredFields();
		
		//遍历activity类的所有属性
		for (Field field : fields) {
			//获得自己属性的数据类型模版
			Class dataType=field.getType();
			//因为activity里面可能定义各种各样的类型
			//而我们只想对控件进行赋值
			//View。class是否可以从dataType进行转化过来
			//所以这个条件就约束了我们只对控件进行处理
			if (View.class.isAssignableFrom(dataType)) {
				//因为我们在activity里面定义的这个属性是私有的  所以要击破封装
				field.setAccessible(true);
				//我们将会自动利用反射技术将所有的控件属性与对应设置ID的控件进行匹配赋值
				try {
					//本质我们是需要利用反射调用我们的findViewById方法
					//因为findViewById这个方法不在我们自己定义的activity里面定义，是从父类继承下来 所以要使用getMethod这个方法
					Method method=cls.getMethod("findViewById", new Class[]{int.class});
					//获得方法中传入的第二个参数值 这个ID 放在R。id这个类里 
					Class idClass=R.id.class;
					//因为我们设计的Activity中的属性名是 特地与它对应的XML中控件ID保持一致 所以field.getName()的返回就是对应的ID的名字
					Field idField=idClass.getDeclaredField(field.getName());
					//第一 因为这个属性是public 所以无需设置access  第二由于这个属性是静态的 所以传入的参数会忽略 所以随便传都OK ，但是不能传入null，会报空指针异常
					Object args=idField.get(idField);
					//调用invoke这个方法，第一个参数是指哪个对象来调用，第二个参数要传入具体的对象控件的ID的值 
					//这里已经调用了findViewById方法
					Object value=method.invoke(activity, new Object[]{args});
					//这里是将findViewById方法的返回值赋值到了对应的属性
					field.set(activity, value);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
}
