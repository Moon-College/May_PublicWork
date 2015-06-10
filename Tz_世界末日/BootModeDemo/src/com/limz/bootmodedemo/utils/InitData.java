package com.limz.bootmodedemo.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.limz.bootmodedemo.activity.R;
import android.util.Log;
import android.view.View;

public class InitData {

	public static void init(Object object) {
		Class cls = object.getClass();
		Field[] fields = cls.getDeclaredFields();
		if (fields == null || fields.length <= 0) {
			Log.e("mingzhu", "初始化失败!");
			return;
		}
		
		for(Field field : fields) {
			field.setAccessible(true);//访问其私有成员
			Class dataType = field.getType();
			if(View.class.isAssignableFrom(dataType)) {
				try {
					Method method = cls.getMethod("findViewById", new Class[]{int.class});
					Class indate = R.id.class;
					Field f = indate.getDeclaredField(field.getName());
					Object args = f.get(new Object());
					Object value = method.invoke(object, args);
					field.set(object, value);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
