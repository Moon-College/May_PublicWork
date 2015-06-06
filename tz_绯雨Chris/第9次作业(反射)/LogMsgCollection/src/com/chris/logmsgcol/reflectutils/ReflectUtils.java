package com.chris.logmsgcol.reflectutils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import android.view.View;
import android.view.View.OnClickListener;

import com.chris.logmsgcol.R;
import com.chris.utils.CLog;

public class ReflectUtils
{
	private static final String TAG = "ReflectUtils";

	public static void initViewsLayout(Object activity) throws ClassNotFoundException,
			NoSuchMethodException, IllegalAccessException, IllegalArgumentException,
			NoSuchFieldException, InvocationTargetException
	{
		Class cls = activity.getClass();
		Field[] viewsFields = cls.getDeclaredFields();
		Method findViewByIdMeth = cls.getMethod("findViewById", new Class[]{ int.class });

		for (int i = 0; i < viewsFields.length; i++)
		{
			CLog.i(TAG, "Modify: " + Modifier.toString(viewsFields[i].getModifiers()));
			CLog.i(TAG, "Type: " + viewsFields[i].getType());
			CLog.i(TAG, "Name: " + viewsFields[i].getName());
			if (View.class.isAssignableFrom(viewsFields[i].getType()))
			{
				viewsFields[i].setAccessible(true);
				Field idParmField = R.id.class.getDeclaredField(viewsFields[i].getName());

				//views = (views)findViewById(R.id.xxx)...
				Object parms = idParmField.get(activity);
				Object value = findViewByIdMeth.invoke(activity, 
						new Object[]{ parms });
				viewsFields[i].set(activity, value);

				//views.setOnClickListener(this activity)
				//或者用这个方法Method setOnClickListenerMeth = View.class.getMethod(
				Method setOnClickListenerMeth = viewsFields[i].get(activity).getClass().getMethod(
						"setOnClickListener", 
						new Class[]{ OnClickListener.class });
				setOnClickListenerMeth.invoke(viewsFields[i].get(activity), 
						new Object[]{ activity });
			}
		}
	}
}
