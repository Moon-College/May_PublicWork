package com.cn.asynctask.utis;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.cn.asynctask.R;
import com.cn.asynctask.R.id;



import android.app.Activity;
import android.util.Log;
import android.view.View;

/**
 * Created on2015-6-6 ����11:28:05 Reflection.java<br/>
 * Author ZhuHequn 1363790125@qq.com <br/>
 * version 1.0 TODO
 */
public class Reflection {
	private static final String TAG = "Reflection";
	// ͨ�����������ģ��
	public static void initView(Activity activity, Class<id> class1) {
		Class<? extends Activity> cls = activity.getClass();
		Field[] field = cls.getDeclaredFields();
		for (Field mfield : field) {
			Class type = mfield.getType();
			if (View.class.isAssignableFrom(type)) {
				Log.i(TAG, "View:" + type.getName());
				// ��Ϊ������activity���涨������������˽�еģ�����Ҫ�����ƻ���װ
				mfield.setAccessible(true);
				try {
					Method method = cls.getMethod("findViewById",
							new Class[] { int.class });
					Class id_cls = R.id.class;
					Field id_field = id_cls.getDeclaredField(mfield.getName());
					Object id_value = id_field.get(id_field);
					Object value = method.invoke(activity, id_value);
					// ��ֵ����Ӧ������
					mfield.set(activity, value);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}

	}
}