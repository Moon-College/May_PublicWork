package com.example.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.util.Log;
import android.view.View;

public class ReflectionUtil {

	public static void initViews(Object activity) {
		// 通过类对象获得类模版
		Class cls = activity.getClass();
		// 获得了我们自己在activity类中定义的一些列属性
		Field[] fields = cls.getDeclaredFields();
		// 循环属性
		for (Field field : fields) {
			// 获得咱们属性的数据类型模版
			Class type = field.getType();
			// 因为acitivity里面可能定义各种各样的类型
			// 而我们只向 对控件进行赋值
			// View.class是否可以从type进行转化过来 --> View view = tv_one;
			// 所以这个条件就约束了 我们只对控件进行处理
			if (View.class.isAssignableFrom(type)) {
				// Log.i("sss", type.getName());
				// 因为我们在activity里面定义的这个属性是私有的，所以要进行破坏封装
				field.setAccessible(true);
				// 我们将会自动利用反射技术将所有的控件属性与对应设置ID的控件进行匹配赋值
				try {
					// 本质我们是需要利用反射调用我们的findViewById方法
					// 因为findViewById这个方法不再我们自定定义的activity里面定义 是从父类继承下来的
					// 所以要使用getMethod
					Method method = cls.getMethod("findViewById",
							new Class[] { int.class });
					// 获得方法中传入的第二个参数的值 这个ID 放在R.id这个类里 第二我们知道具体的对应的属性名字
					Class id_cls = R.id.class;
					// 因为我们的设计Activity中的属性名是 特地与他的ID保持一致
					// 所以field.geName()的返回就是对应ID的名字
					Field id_field = id_cls.getDeclaredField(field.getName());
					// 第一因为这个属性是public 所以无需设置setAccessible 第二由于这个属性是静态的
					// 所以传入的参数会忽略 所以随便传都OK
					// 这里已经获得了ID的值
					Object id_value = id_field.get(id_field);
					// 我要调用这个方法 第一个参数是指 哪一个对象来调用 第二个参数 要传入具体的对象控件的ID的值
					// 这里已经调用了findViewById方法
					Object value = method.invoke(activity, id_value);
					// 这里是将findViewById方法的返回值 赋值到对应的属性
					field.set(activity, value);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		// this.findViewById(R.id.tv_one)
	}
}
