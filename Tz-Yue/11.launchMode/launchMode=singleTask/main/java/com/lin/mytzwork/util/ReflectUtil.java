package com.lin.mytzwork.util;

import android.app.Activity;
import android.app.Fragment;
import android.util.Log;
import android.view.View;

import com.lin.mytzwork.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2015/6/6.
 */
public class ReflectUtil {
    private static final String TAG = "ReflectUtil";

    public static void initView(Object activity) {
        Class<?> idClass = R.id.class; //引用的类
        Class aClass = activity.getClass();

        List<Field> list = new ArrayList<>();
        addFieldList(list, aClass, new Class[]{Activity.class, Fragment.class}); //获得所有的变量

        for (Field field : list) {
            Class<?> fieldType = field.getType();
            if (View.class.isAssignableFrom(fieldType)) { //能否转换为View.Class
                field.setAccessible(true); //破封装
                try {
                    Field idField = null;
                    try {
                        idField = idClass.getDeclaredField(field.getName());
                    } catch (NoSuchFieldException e) {
                        Log.w(TAG, field.getGenericType() + " " + field.getName() + ": not found Control Id in R.id.class");
                        //e.printStackTrace();
                        continue;
                    }
                    Object args = idField.get(idField);
                    Method findViewById = aClass.getMethod("findViewById", new Class[]{int.class});
                    Object value = findViewById.invoke(activity, new Object[]{args});

                    if (value == null) {
                        Log.w(TAG, field.getGenericType() + " " + field.getName() + ": not get Value");
                        continue;
                    }

                    field.set(activity, value);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 获得当前类所有的变量
     *
     * @param list       存入的集合
     * @param mClass     当前的类
     * @param superClass 到这些父类停止
     */
    public static void addFieldList(List list, Class mClass, Class... superClass) {
        if (superClass != null &&
                superClass.length > 0 &&
                !Arrays.asList(superClass).contains(mClass.getSuperclass())) {
            addFieldList(list, mClass.getSuperclass(), superClass);
        }

        Field[] declaredFields = mClass.getDeclaredFields();
        list.addAll(Arrays.asList(declaredFields));

    }
}
