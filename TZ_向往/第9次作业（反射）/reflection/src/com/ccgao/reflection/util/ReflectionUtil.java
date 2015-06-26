package com.ccgao.reflection.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.view.View;

import com.ccgao.reflection.R;

public class ReflectionUtil {
	public static void initView(Object activity){
		Class cls=activity.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			//得到属性数据类型
			Class type = field.getType();
			//因为所以的控件都继承于view，所以可以测试是否能转换为view，以过滤掉其他的变量属性
			if(View.class.isAssignableFrom(type)){
				//private的属性要进行封装破坏，如下
				field.setAccessible(true);
				//以下代码等同于调用tv_1 = (TextView) findViewById(R.id.tv_1);
				Method method;
				try {
					method = cls.getMethod("findViewById", new Class[]{int.class});
					Object args = null;
					//args参数需从R文件中获取，R文件下有个id类，下边public变量存的值就是 public static final int tv_1=0x7f050000;
					Class rClas=R.id.class;
					Field rRield = rClas.getDeclaredField(field.getName());
					args = rRield.get(rRield);  //因为是public，所以不用setAccessible(true); 而且静态变量rRield这个参数是忽略掉的，随便传都行，但不能是null
					//进行调用
					Object value = method.invoke(activity, new Object[]{args});
					//tv_1 = 赋值
					field.set(activity, value);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
	}
}
