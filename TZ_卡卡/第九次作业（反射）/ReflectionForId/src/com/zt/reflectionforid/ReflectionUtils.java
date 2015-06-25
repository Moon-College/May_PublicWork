package com.zt.reflectionforid;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.app.Activity;
import android.view.View;

public class ReflectionUtils {

	public static void initViews(Activity activity) {
		Class<? extends Activity> class1 = activity.getClass();
		// 获得声明的属性数组
		Field[] fields = class1.getDeclaredFields();
		for (Field field : fields) {
			// 获得声明属性的类型
			Class type = field.getType();
			// type 是否 是view类型的
			if (View.class.isAssignableFrom(type)) {

				// 利用反射技术 findViewById
				Object value = null;
				try {
					Method method = class1.getMethod("findViewById",
							new Class[] { int.class });
					//得到R文件类
					Class idcls=R.id.class;
					//根据名字得到声明的id
					Field idField = idcls.getDeclaredField(field.getName());
					Object id = idField.get(idField);
					//属性名和id名称一样 执行findViewById 
					Object invoke = method.invoke(activity, new Object[]{id});
					field.setAccessible(true);
					field.set(activity, invoke);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			// System.out.println(type);
		}

	};
}
