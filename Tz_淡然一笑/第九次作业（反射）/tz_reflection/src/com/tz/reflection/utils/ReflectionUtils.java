package com.tz.reflection.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import com.tz.reflection.R;
import android.util.Log;
import android.view.View;

/**
 * 反射工具类
 */
public class ReflectionUtils {

	private static final String TAG = "info";

	public static void initViews(Object activity) {
		// 通过对象获得类模板
		Class cls = activity.getClass();
		// 获得了我们自己定义的activity类的一系列属性 getFields()方法是获得类继承下来的属性列表
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			// activity定义属性是私有的，反射强行击破封装
			field.setAccessible(true);
			// 获取属性的数据类型模板
			Class dataType = field.getType();
			// 因为activity里面可能定义各种各样的数据类型，而我们只对控件进行赋值
			// View.class是否可以从dataType类型转换过来，所以这个条件就约束了我们只对控件进行处理
			if (View.class.isAssignableFrom(dataType)) {
				Log.i(TAG, dataType.getName());
				// 日志：android.widget.TextView   android.widget.EditText
				Log.i(TAG, field.getName());
				// 日志：tv_one  et_input
				try {
					// 所以我们将会自动利用发射技术,将所有的控件属性与对应设置的id控件进行匹配赋值
					// 利用反射调用findViewById方法
					// 因为findViewById方法不在我们定义的MainActivity中，它是从父类继承下来的，所以我们要调用getMethod()方法
					Method method = cls.getMethod("findViewById", new Class[] { int.class });
					// 获得方法中传入的第二个参数的值【id】,1 这个id放在R.id这里类中，2 我们知道具体对应的属性的名字
					Class idCls = R.id.class;
					// 因为我们设计Activity的属性名，与它对应的id保持一致，所以field.getName()的返回就是对应的id的名字
					Field idField = idCls.getDeclaredField(field.getName());
					// 1 这个属性是public,所以无需设置access,2
					// 由于这个属性是静态的，所以传入的参数会忽略，随便传参数都可以。这里已经获得了id的值
					Object args = idField.get(idField);
					// 调用invoke这个方法，第一个参数是指哪个对象来调用，第二个参数要传入的具体的对象的id的值
					// 这里已经调用了findViewById方法
					Object value = method.invoke(activity, new Object[] { args });
					// 将findViewById()方法的返回值赋值到对应的属性
					field.set(activity, value);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

}
