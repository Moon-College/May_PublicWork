package com.decent.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.app.Activity;
import android.util.Log;
import android.view.View;

public class ReflictionUtil {

	private static final String TAG = "ReflictionUtil";

	/**
	 * 
	 * 
	 * @param activityName activity类的名字
	 * @param resourceName R.java类的方法
	 * @param activity activity的object
	 */
	public static void InjectionView(String activityName,String resourceName,Activity activity)
	{
		// 1、通activity的object获取Activity的declareFields数组
		Class activityCls = null;
		try
		{
			activityCls = Class
					.forName(activityName);
		} catch (ClassNotFoundException e2)
		{
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if (activityCls == null)
		{
			Log.d(TAG,
					"get com.decent.decentrefliction.activity class failed");
			return;
		}
		Field[] activityFileds = activityCls.getDeclaredFields();

		/*
		 * 获取findViewById方法,getMethod是获取父类的方法
		 */
		Method method = null;
		try
		{
			method = activityCls.getMethod("findViewById", int.class);
		} catch (NoSuchMethodException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (method == null)
		{
			Log.e(TAG, "get method findViewById failed!");
			return;
		}

		/*
		 * 根据"com.decent.decentrefliction.R.id"获取resourceIdCls类
		 * 1、由于是子类所以需要写成com.decent.decentrefliction.R$id
		 * 2、或者先获取"com.decent.decentrefliction.R"，在通过查找子类里面以id结尾的类
		 */
		Class resourceIdCls = null;
		try
		{
			resourceIdCls = Class.forName(resourceName+"$id");
			/*
			Class resourceCls = Class.forName("com.decent.decentrefliction.R");
			Class[] resourceSubClass = resourceCls.getDeclaredClasses();
			for (Class subClass : resourceSubClass)
			{
				Log.d(TAG, "subClass:" + subClass.getName());
				if (subClass.getName().endsWith("id"))
				{
					resourceIdCls = subClass;
					break;
				}
			}
			*/
		} catch (ClassNotFoundException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (resourceIdCls == null)
		{
			Log.e(TAG, "get com.decent.decentrefliction.R class failed");
			return;
		}

		// 2、遍历declareFiled
		for (Field field : activityFileds)
		{
			// 根据getType判断是否是View的子类，如果是view的子才赋值
			// isAssignableFrom这个表示view是否是field.getType()的父类
			// 如果是父类则可以根据多态把子类赋值给父类型的变量
			if (View.class.isAssignableFrom(field.getType()))
			{
				try
				{
					/*
					 * 获取R.id.xxxx这样的字段对应的值 由于resourceIdCls是public
					 * static的类，所以不需要实例化 idField.get(resourceIdCls)直接传class就可以了
					 * 而且所有的字段也都是public所以不需要field.setAccessible(true)
					 * 这个方法要求，MainActivity里面控件字段的名字和layout文件里面控件的id名字一致 *
					 */
					Field idField = resourceIdCls.getField(field.getName());
					int id = (Integer) idField.get(resourceIdCls);

					/*
					 * 由于我们需要设置的那些字段都是private的，对外不可见的所以需要setAccessible为true
					 * 才能set
					 */
					field.setAccessible(true);
					field.set(activity, method.invoke(activity, id));
					field.setAccessible(false);
				} catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}
