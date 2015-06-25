package com.yl.tools;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.view.View;

import com.yl.reflection.R;

public class InitView {
	public static void init(Object view) {
		try {
			Class cls = view.getClass();
			Field[] fields = cls.getDeclaredFields();
			for (Field f : fields) {
				
				if (View.class.isAssignableFrom(f.getType())) {
					Class cla = R.id.class;
					Field field = cla.getDeclaredField(f.getName());

					Method fileMethod = cls.getMethod("findViewById",
							new Class[] { int.class });

					Object args = field.get(field);
					Object value = fileMethod.invoke(view,
							new Object[] { args });

					f.setAccessible(true);
					f.set(view, value);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
