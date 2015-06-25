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
		// 获取配置文件自定义的id和name映射关系
		getMap(activity);
		Class rootClass = activity.getClass();
		// 获取传入对象定义的所有属性
		Field[] rootClassFields = rootClass.getDeclaredFields();
		// 获取findViewById方法
		try {
			Method initMethod = rootClass.getMethod("findViewById",
					new Class[] { int.class });
			for (Field field : rootClassFields) {
				// 获取并判断数据类型是否为View的子类
				Class fieldType = field.getType();
				if (View.class.isAssignableFrom(fieldType)) {
					// 将所有的私有属性强制能访问
					field.setAccessible(true);
					
					// 获取id
					Class idClass = R.id.class;
					// 从映射文件找属性名对应的id名
					String idName = mappings.get(field.getName());
					if (TextUtils.isEmpty(idName)) {
						idName = field.getName();
					}
					// 得到id值
					Field idField = idClass.getDeclaredField(idName);
					Object args = idField.get(idClass);
					
					// 通过id获取到
					Object value = initMethod.invoke(activity, args);
					
					// 设置view
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
					Log.d("PADER INFO", "开始解析");
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
