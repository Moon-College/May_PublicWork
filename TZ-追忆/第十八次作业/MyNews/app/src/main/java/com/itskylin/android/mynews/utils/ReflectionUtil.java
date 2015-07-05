package com.itskylin.android.mynews.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.itskylin.android.mynews.R;


public class ReflectionUtil {
	private static final String TAG = "ReflectionUtil";

	public static void initView(Activity activity) {

		Class<? extends Activity> cls = activity.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			Class clsType = field.getType();
			if (View.class.isAssignableFrom(clsType)) {
				Log.i(TAG, "View:" + clsType.getName());
				try {
					Method method = cls.getMethod("findViewById", int.class);
					Class idCls = R.id.class;
					Field idField = idCls.getDeclaredField(field.getName());
					Object value = idField.getInt(idField);

					Object setValue = method.invoke(activity, value);
					field.set(activity, setValue);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
