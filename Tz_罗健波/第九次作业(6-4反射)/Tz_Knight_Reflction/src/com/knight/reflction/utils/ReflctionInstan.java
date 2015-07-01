package com.knight.reflction.utils;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.R.string;
import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;

import com.knight.reflction.R;

public class ReflctionInstan {

	public static Map<String,String> mappings = new HashMap<String, String>();
	public static void Instance(Activity activity) {
		getMap(activity);
		// 拿到activity类的模板
		Class actCls = activity.getClass();
		// 拿到类的属性集合Fields
		Field[] fields = actCls.getDeclaredFields();
		// 循环属性
		try {
			/**
			 * 拿到findViewById方法 由于 这个方法不是我们自己定义在activity中的 所以 调用getMethod
			 * 是调用父类继承过来的 方法 传递的参数 是在R.id类里，
			 */
			Method findMethod = actCls.getMethod("findViewById",
					new Class[] { int.class });
			Class idCls = R.id.class;
			for (Field field : fields) {
				/**
				 * 由于activity中的属性是私有的 需要强行击破封装
				 */
				field.setAccessible(true);
				/**
				 * 获得属性数据的类型模板
				 * 我们定义的activity中属性内容很多
				 * 只需要拿出控件来赋值
				 */
				Class dataType = field.getType();
				//从映射文件中找到属性对应的ID
				String idName = mappings.get(field.getName());
				if (TextUtils.isEmpty(idName)) {
					idName = field.getName();
				}
				//获得id 类属性
				Field idField = idCls.getDeclaredField(idName);
				//由于R.id类中属性是public的 而且还是静态 所以传入的值会被忽略 拿到ID值
				Object args = idField.get(idCls);
				//这个方法的两个参数 第一个是哪个对象调用 第二个是要传入的具体对象控件的ID值 这里已经调用了findViewById
				Object value = findMethod.invoke(activity, new Object[]{args});
				/**
				 * 给控件赋值 需要的参数是 要给哪个对象的属性赋值 以及需要赋值的内容 findViewById
				 */
				field.set(activity, value);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * 通过MAP解析属性对应的ID
	 * @Title: getMap
	 * @Description: TODO
	 * @param activity
	 * @return
	 * @return: Map<String,String>
	 */
	private static Map<String, String> getMap(Activity activity) {
		InputStream is = activity.getResources().openRawResource(R.raw.mapping);
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(is,"UTF-8");
			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					Log.e("INFO", "开始解析");
					break;
				case XmlPullParser.START_TAG:
					String tagName = parser.getName();
					Log.e("PARSER INFO TAG START", tagName);
					if ("view".equals(tagName)) {
						String viewName = parser.getAttributeValue(0);
						String viewId = parser.getAttributeValue(1);
						mappings.put(viewName, viewId);
					}
					break;
				case XmlPullParser.END_TAG:
					Log.e("LOG TAG END", "");
					break;
				default:
					break;
				}
				eventType = parser.next();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return mappings;	
	}
}
