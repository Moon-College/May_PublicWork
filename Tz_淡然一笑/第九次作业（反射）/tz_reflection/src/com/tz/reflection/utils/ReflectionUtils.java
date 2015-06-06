package com.tz.reflection.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import com.tz.reflection.R;
import android.util.Log;
import android.view.View;

/**
 * ���乤����
 */
public class ReflectionUtils {

	private static final String TAG = "info";

	public static void initViews(Object activity) {
		// ͨ����������ģ��
		Class cls = activity.getClass();
		// ����������Լ������activity���һϵ������ getFields()�����ǻ����̳������������б�
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			// activity����������˽�еģ�����ǿ�л��Ʒ�װ
			field.setAccessible(true);
			// ��ȡ���Ե���������ģ��
			Class dataType = field.getType();
			// ��Ϊactivity������ܶ�����ָ������������ͣ�������ֻ�Կؼ����и�ֵ
			// View.class�Ƿ���Դ�dataType����ת���������������������Լ��������ֻ�Կؼ����д���
			if (View.class.isAssignableFrom(dataType)) {
				Log.i(TAG, dataType.getName());
				// ��־��android.widget.TextView   android.widget.EditText
				Log.i(TAG, field.getName());
				// ��־��tv_one  et_input
				try {
					// �������ǽ����Զ����÷��似��,�����еĿؼ��������Ӧ���õ�id�ؼ�����ƥ�丳ֵ
					// ���÷������findViewById����
					// ��ΪfindViewById�����������Ƕ����MainActivity�У����ǴӸ���̳������ģ���������Ҫ����getMethod()����
					Method method = cls.getMethod("findViewById", new Class[] { int.class });
					// ��÷����д���ĵڶ���������ֵ��id��,1 ���id����R.id�������У�2 ����֪�������Ӧ�����Ե�����
					Class idCls = R.id.class;
					// ��Ϊ�������Activity����������������Ӧ��id����һ�£�����field.getName()�ķ��ؾ��Ƕ�Ӧ��id������
					Field idField = idCls.getDeclaredField(field.getName());
					// 1 ���������public,������������access,2
					// ������������Ǿ�̬�ģ����Դ���Ĳ�������ԣ���㴫���������ԡ������Ѿ������id��ֵ
					Object args = idField.get(idField);
					// ����invoke�����������һ��������ָ�ĸ����������ã��ڶ�������Ҫ����ľ���Ķ����id��ֵ
					// �����Ѿ�������findViewById����
					Object value = method.invoke(activity, new Object[] { args });
					// ��findViewById()�����ķ���ֵ��ֵ����Ӧ������
					field.set(activity, value);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

}
