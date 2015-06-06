package com.limz.reflectiondemo.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.limz.reflectiondemo.activity.R;

import android.app.Activity;
import android.util.Log;
import android.view.View;

public class FindViewId {

	public static void initView(Activity activity) {
		Log.d("mingzhu", "init start");
		Class cls = activity.getClass();
		Field[] fields = cls.getDeclaredFields();
		for(Field field : fields) {
			field.setAccessible(true);
			Class type = field.getType();
			Log.d("mingzhu", "type = " + type);
			if(View.class.isAssignableFrom(type)) {
				//获取findViewByID方法
				try {
					Method method = cls.getMethod("findViewById", new Class[]{int.class});
					Class idClass = R.id.class;
					Field field2 = idClass.getDeclaredField(field.getName());
					Object args = field2.get(new Object());
					Object value = method.invoke(activity, new Object[]{args});
					field.set(activity, value);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		Log.d("mingzhu", "init end");
	}
}
