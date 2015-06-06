package com.tz.refectandroid.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import android.view.View;

import com.tz.refectandroid.R;

public class Utils {
	public static Map<String, String> mappings = new HashMap<String, String>();

	public static void initView(Activity activity) {
		// ��ȡ�����ļ��Զ����id��nameӳ���ϵ
		getMap(activity);
		Class rootClass = activity.getClass();
		// ��ȡ������������������
		Field[] rootClassFields = rootClass.getDeclaredFields();
		// ��ȡfindViewById����
		try {
			Method initMethod = rootClass.getMethod("findViewById",
					new Class[] { int.class });
			for (Field field : rootClassFields) {
				// ��ȡ���ж����������Ƿ�ΪView������
				Class fieldType = field.getType();
				if (View.class.isAssignableFrom(fieldType)) {
					// �����е�˽������ǿ���ܷ���
					field.setAccessible(true);
					
					// ��ȡid
					Class idClass = R.id.class;
					// ��ӳ���ļ�����������Ӧ��id��
					String idName = mappings.get(field.getName());
					if (TextUtils.isEmpty(idName)) {
						idName = field.getName();
					}
					// �õ�idֵ
					Field idField = idClass.getDeclaredField(idName);
					Object args = idField.get(idClass);
					
					// ͨ��id��ȡ��
					Object value = initMethod.invoke(activity, args);
					
					// ����view
					field.set(activity, value);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Map<String, String> getMap(Context context) {
		InputStream in = context.getResources().openRawResource(
				R.raw.view_mapping);

		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(in, "UTF-8");
			int eventType = parser.getEventType();
			String mappingName;
			while (eventType != XmlPullParser.END_DOCUMENT) {

				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					Log.d("PADER INFO", "��ʼ����");
					break;
				case XmlPullParser.START_TAG:
					String tagName = parser.getName();
					Log.d("PADER INFO TAG Start", tagName);
					if ("view".equals(tagName)) {
						String viewName = parser.getAttributeValue(0);
						String viewId = parser.getAttributeValue(1);
						mappings.put(viewName, viewId);
					}
					break;

				case XmlPullParser.END_TAG:
					Log.d("PADER INFO TAG end", "");
				default:
					break;
				}
				eventType = parser.next();
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mappings;
	}
}
