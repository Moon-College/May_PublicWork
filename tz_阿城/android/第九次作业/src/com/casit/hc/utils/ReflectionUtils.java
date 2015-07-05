package com.casit.hc.utils;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.R;
import android.app.Activity;
import android.view.View;

public class ReflectionUtils extends Activity{
    public static void initViews(Activity activity) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, NoSuchMethodException{
    	Class cls = activity.getClass();
       //获得一系列的属性
    	Field[] fields = cls.getDeclaredFields();
    	//循环属性
    	for(Field field:fields){
    		Class dataType = field.getType();
    	  //VIEW是否能从后面的TYPE 转换过来
    		//所以这个条件就约束了值对控件执行
    		if(View.class.isAssignableFrom(dataType)){
    			//自动利用反射技术
    			Method method = cls.getMethod("findViewById", new Class[]{int.class});
    	       //  = null;
    			Class idCls = R.id.class;
    			//设置属性名和ID保持一致，所以对应的是ID的名字与属性一致
    			Field idField =idCls.getDeclaredField(field.getName());
    		//	Field idField = 
    		    Object args = idField.get(idField);
    		    Object value = method.invoke(activity, new Object[]{args});
    		//	Object value = method.invoke(activity, new Object[]{args});
    		    field.setAccessible(true);
    		    field.set(activity, value);
    		
    		}
    	  
    	}
    	
    }
}
