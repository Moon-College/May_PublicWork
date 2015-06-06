package com.decent.decentrefliction.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.util.Log;
import android.view.View;

public class ReflictionUtil
{

	private static final String TAG = "ReflictionUtil";

	/**
	 * 
	 * @param activity
	 */
	public static void InjectionView(Activity activity)
	{
		// 1��ͨactivity��object��ȡActivity��declareFields����
		Class activityCls = null;
		try
		{
			activityCls = Class
					.forName("com.decent.decentrefliction.activity.MainActivity");
		} catch (ClassNotFoundException e2)
		{
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if (activityCls == null)
		{
			CustomLog.d(TAG,
					"get com.decent.decentrefliction.activity class failed");
			return;
		}
		Field[] activityFileds = activityCls.getDeclaredFields();

		/*
		 * ��ȡfindViewById����,getMethod�ǻ�ȡ����ķ���
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
			CustomLog.e(TAG, "get method findViewById failed!");
			return;
		}

		/*
		 * ����"com.decent.decentrefliction.R.id"��ȡresourceIdCls��
		 * 1������������������Ҫд��com.decent.decentrefliction.R$id
		 * 2�������Ȼ�ȡ"com.decent.decentrefliction.R"����ͨ����������������id��β����
		 */
		Class resourceIdCls = null;
		try
		{
			resourceIdCls = Class.forName("com.decent.decentrefliction.R$id");
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
			CustomLog.e(TAG, "get com.decent.decentrefliction.R class failed");
			return;
		}

		// 2������declareFiled
		for (Field field : activityFileds)
		{
			// ����getType�ж��Ƿ���View�����࣬�����view���ӲŸ�ֵ
			// isAssignableFrom�����ʾview�Ƿ���field.getType()�ĸ���
			// ����Ǹ�������Ը��ݶ�̬�����ำֵ�������͵ı���
			if (View.class.isAssignableFrom(field.getType()))
			{
				try
				{
					/*
					 * ��ȡR.id.xxxx�������ֶζ�Ӧ��ֵ ����resourceIdCls��public
					 * static���࣬���Բ���Ҫʵ���� idField.get(resourceIdCls)ֱ�Ӵ�class�Ϳ�����
					 * �������е��ֶ�Ҳ����public���Բ���Ҫfield.setAccessible(true)
					 * �������Ҫ��MainActivity����ؼ��ֶε����ֺ�layout�ļ�����ؼ���id����һ�� *
					 */
					Field idField = resourceIdCls.getField(field.getName());
					int id = (int) idField.get(resourceIdCls);

					/*
					 * ����������Ҫ���õ���Щ�ֶζ���private�ģ����ⲻ�ɼ���������ҪsetAccessibleΪtrue
					 * ����set
					 */
					field.setAccessible(true);
					field.set(activity, method.invoke(activity, id));
					field.setAccessible(false);
				} catch (NoSuchFieldException | IllegalAccessException
						| InvocationTargetException | IllegalArgumentException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
