package com.vincen.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.view.View;

public class ReflectionUtil {

	public static void initView(Activity activity) {
		Class<? extends Activity> cls = activity.getClass();
		// 获取activity所定义的属性
		try {
			Field[] fields = cls.getDeclaredFields();
			Method method = cls.getMethod("findViewById",
					new Class[] { int.class });
			for (Field field : fields) {
				// 获取属性的类型
				Class type = field.getType();
				// 判断type是否可以转换成View
				if (View.class.isAssignableFrom(type)) {
					Class idCls = R.id.class;
					// 根据属性名获取id
					Field idField = idCls.getDeclaredField(field.getName());
					
					Object args = idField.get(idField);
					//初始化获取对应的控件  相当于findViewById
					Object value = method.invoke(activity, new Object[]{args});
					//在MainActivity类中定义的属性是私有的，所有需重新打乱在封装
					field.setAccessible(true);
					field.set(activity, value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
