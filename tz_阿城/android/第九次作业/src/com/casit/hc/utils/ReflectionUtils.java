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
       //���һϵ�е�����
    	Field[] fields = cls.getDeclaredFields();
    	//ѭ������
    	for(Field field:fields){
    		Class dataType = field.getType();
    	  //VIEW�Ƿ��ܴӺ����TYPE ת������
    		//�������������Լ����ֵ�Կؼ�ִ��
    		if(View.class.isAssignableFrom(dataType)){
    			//�Զ����÷��似��
    			Method method = cls.getMethod("findViewById", new Class[]{int.class});
    	       //  = null;
    			Class idCls = R.id.class;
    			//������������ID����һ�£����Զ�Ӧ����ID������������һ��
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
