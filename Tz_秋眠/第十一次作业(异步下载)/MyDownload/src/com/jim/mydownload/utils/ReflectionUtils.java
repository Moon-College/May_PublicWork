package com.jim.mydownload.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.view.View;

public class ReflectionUtils {
	/**
	 * 用反射写findViewById方法， 注意:使用此方法控件id要与类里面定义的对象名称一致
	 * 
	 * @param activity
	 *            传递要被反射的activity
	 * @param c
	 *            传递当前的应用的R文件类
	 */
	public static void initViews(Activity activity, Class R) {
		// 通过传递过来的activity对象获得其所在的类(这里指Activity)
		Class cls = activity.getClass();
		// 通过获得的类取得在该类中定义的属性
		Field[] fields = cls.getDeclaredFields();
		// 遍历属性数组中的属性
		for (Field field : fields) {
			// 设置可以击穿封装(private属性)
			field.setAccessible(true);
			// 获取当前属性的类型：如View类，long类等等
			Class dataType = field.getType();
			// 判断该类是否可以转换为View类，也就是View的子类
			if (View.class.isAssignableFrom(dataType)) {
				try {
					// 通过findViewById字符串找到findViewById方法
					Method method = cls.getMethod("findViewById",// 方法名
							new Class[] { int.class });// 传递进入该方法的类型
					// 获取R.id类，为了取控件的id
					Class idCls = R;
					// 通过类里面相应的对象名称获得R.id类里面相应名称的对象,所以，控件id要与类里面定义的对象名称一致
					Field idField = idCls.getDeclaredField(field.getName());
					// 获取R.id类中的对应的int值，存放在args里面，这里因为R文件中的类都是static静态的，
					// 所以随便传个值，会被忽略，一般传入Object对象
					Object args = idField.get(idField);
					// 使用findViewById方法
					// 此时的method就是findViewById方法，在传入的activity类中使用，参数为args
					Object value = method.invoke(activity,
							new Object[] { args });
					// 设置返回值到activity中，value相当于R.id.***;
					// 相当于使用了，如TextView tv1 =
					// (TextView)activity.findViewById(R.id.tv1);
					field.set(activity, value);
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
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
