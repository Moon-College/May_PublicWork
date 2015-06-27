package com.knight.dialog.ref;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.knight.dialog.R;
import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import android.view.View;

/**
 * 反射简化findViewById
 * 
 * @ClassName: RefInitView
 * @Description: TODO
 * @author: Knight
 * @date: 2015-6-25 上午11:10:14
 */
public class RefInitView {

	private static Map<String, String> mapping = new HashMap<String, String>();
	public static void findViewById(Activity activity) {
		/**
		 * 首先通过activity拿到Class模具 ，通过class拿到对象的属性Field
		 * 拿到R.id.class类中的静态属性，拿到findViewById方法 这个方法 
		 * 不在我们自己写的activity中而是在父类中
		 */
		//获取属性对应的ID
		getMap(activity);
		
		// 拿到属于activity的类模板
		Class actCls = activity.getClass();
		// 拿到类的对象的属性集合
		Field[] fields = actCls.getDeclaredFields();
		// 拿到R.id类
		Class idCls = R.id.class;

		try {
			//拿到父类的findViewById方法 第二个参数是方法要传入的参数类型
			Method method = actCls.getMethod("findViewById", new Class[] { int.class });
			// activity中定义的属性很多 要拿到 View控件的属性
			// 循环遍历属性
			for (Field field : fields) {
				//获得属性的数据类型模板
				Class dataType = field.getType();
				//View.class是否可以从dataType转换过来
				if (View.class.isAssignableFrom(dataType)) {
					// 属性都是private的需要击破封装
					field.setAccessible(true);
					//拿到map中存储的属性ID值
					Log.e("INFO", mapping.get(field.getName()));
					String idName = mapping.get(field.getName());
					if (TextUtils.isEmpty(idName)) {
						idName = field.getName();
					}
					//拿到R.id类的属性
					Field idField = idCls.getDeclaredField(idName);
					//拿到R.id类的属性的值 因为属性是public 而且是static的所以idField.get(参数可以随便传入但是不能为null)
					Object args = idField.get(idField);
					Object value = method.invoke(activity, new Object[]{args});
					field.set(activity, value);
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 解析XML拿到属性对应的id
	 * @Title: getMap
	 * @Description: TODO
	 * @param context
	 * @return
	 * @return: Map<String,String>
	 */
	public static Map<String, String> getMap(Context context){
		//获取要解析的XML文件
		InputStream is = context.getResources().openRawResource(R.raw.setting);
		/**
		 * 利用XmlPullParser 解析XML
		 * 读取XML声明返回START_DOCUMENT,读取XML的结束返回END_DOCUMENT
		 * 读取XML的开始标签返回START_TAG,读取XML的结束标签返回END_TAG
		 * 读取XML的文本返回 TEXT
		 */
		XmlPullParser parser = Xml.newPullParser(); 
		
		try {
			//设置解析编码
			parser.setInput(is, "UTF-8");
			//get event type 事件类型
			int eventType = parser.getEventType();
			//continue to end document 循环读取文档内容
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_TAG:
					//标签名
					String tagName = parser.getName();
					if ("view".equals(tagName)) {
						//拿到每个标签的内容 name , id用 map 保存起来
						String viewName = parser.getAttributeValue(0);
						String viewId = parser.getAttributeValue(1);
						mapping.put(viewName, viewId);
					}
					break;
				default:
					break;
				}
				eventType = parser.next();
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mapping;
	}
}
