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

    /**
     * 初始化继承了Activity的所有View控件变量名与控件id 控件
     * <p/>
     * 包含父类控件
     *
     * @param activity
     */
    public static void initView(Object activity) {
        Class<?> idClass = R.id.class; //引用的类
        Class aClass = activity.getClass();

        List<Field> list = new ArrayList<>();
        addFieldList(list, aClass, new Class[]{Activity.class}); //获得所有的变量

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
                    } catch (Exception e) {
                        e.printStackTrace();
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
                } catch (Exception e) {
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

    /**
     * 初始化指定类中 View控件名与View中 一致 的变量<p/>
     * 不包含父类控件
     *
     * @param obj  任意包含View类型控件的类
     * @param view 包含这些控件的视图
     * @param <T>
     */
    public static <T> void initView(T obj, View view) {

        Class<?> idClass = R.id.class; //引用的类
        Class aClass = obj.getClass(); //拥有View控件的类
        Class<View> viewClass = View.class;
        Field[] fields = aClass.getDeclaredFields();

        for (Field field : fields) {
            Class<?> fieldType = field.getType();
            if (View.class.isAssignableFrom(fieldType)) {
                field.setAccessible(true); //破封装
                Field idField = null;
                /*R文件中拿到这个属性*/
                try {
                    idField = idClass.getDeclaredField(field.getName());
                } catch (NoSuchFieldException e) {
                    Log.w(TAG, field.getGenericType() + " " + field.getName() + ": not found Control Id in R.id.class");
                    //e.printStackTrace();
                    continue;
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }

                try {
                    Object args = idField.get(idField); //R文件中拿到这个值
                    //View中拿到findViewById方法
                    Method findViewById = viewClass.getMethod("findViewById", new Class[]{int.class});
                    //执行findViewById方法
                    Object value = findViewById.invoke(view, args);

                    if (value == null) {
                        Log.w(TAG, field.getGenericType() + " " + field.getName() + ": not get Value");
                        continue;
                    }
                    //设置到需要绑定的类中去
                    field.set(obj, value);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }


    }
}
