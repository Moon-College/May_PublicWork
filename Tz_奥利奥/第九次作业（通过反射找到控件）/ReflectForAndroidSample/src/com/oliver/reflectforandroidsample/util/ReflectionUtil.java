package com.oliver.reflectforandroidsample.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.oliver.reflectforandroidsample.R;

import android.view.View;

/**
 * 反射工具类
 * @author Oliver
 *
 */
public class ReflectionUtil {

	/**
	 * 通过对对象的反射对对象的属性进行初始化
	 * @param obj	需要初始化的对象
	 */
	public static final void initView(Object obj){
		//得到传入对象的相关属性
		Class<?> objClass = obj.getClass();
		Field[] fields = objClass.getDeclaredFields();
		//循环获取的属性，对符合初始化的类型的属性进行初始化
		for (Field field : fields) {
			//得到每个属性的数据类型
			Class<?> dataType = field.getType();
			//判断数据类型是否可以转换成View
			if(View.class.isAssignableFrom(dataType)){
				//得到对象属性初始化的方法
				try {
					Method initMethod = objClass.getMethod("findViewById", new Class[]{int.class});
					//得到调用方法是需要传入的参数
					//读取R文件中的id类
					Class<?> idClass = R.id.class;
					//根据传入参数的属性名得到在R文件对应id类中的Id
					Field idField = idClass.getDeclaredField(field.getName());
					//调用findViewById方法
					//参数一为方法的归属者，参数二为调用该方法是需要传入的参数
					Object initObj = initMethod.invoke(obj, new Object[]{idField.get(idClass)});
					//赋值给对应参数
					field.setAccessible(true);
					field.set(obj, initObj);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
