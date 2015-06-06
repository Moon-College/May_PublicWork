package com.tz.reflection.util;

import android.app.Activity;
import android.view.View;
import com.tz.reflection.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by qinhan on 15/6/5.
 */
public class ReflectionUtils {
    public static void initViews(Activity activity) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        Class cls = activity.getClass();
        Field[] fields = cls.getDeclaredFields();
        Method method = cls.getMethod("findViewById", new Class[]{int.class});
        for (Field field : fields) {
            Class attr = field.getType();
            if (View.class.isAssignableFrom(attr)) {
                Class idClass= R.id.class;
                Field viewId = idClass.getDeclaredField(field.getName());
                Object idValue = viewId.get(viewId);
                Object view = method.invoke(activity, new Object[]{idValue});
                field.setAccessible(true);
                field.set(activity, view);
            }
        }
    }
}
