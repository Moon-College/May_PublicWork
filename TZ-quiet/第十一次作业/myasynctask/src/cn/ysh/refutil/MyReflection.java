package cn.ysh.refutil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import cn.ysh.myasynctask.R;

import android.util.Log;
import android.view.View;

public class MyReflection {
	
	public static void initView(Object activity, String methodName){
		Class cls = activity.getClass();
		Field[] children = cls.getDeclaredFields();
		try {
			Method method = cls.getMethod(methodName, new Class[]{int.class});
			for(Field field : children){
				Log.d("type", ""+field.getType());
				//是否为控件
				if(View.class.isAssignableFrom(field.getType())){
					Class idCls = R.id.class;
					Field idChildren = idCls.getDeclaredField(field.getName());
					//如果为静态的Field,get(Object object)的object被忽略
					Object args = idChildren.get(idChildren);
					field.setAccessible(true);
					Object obj =method.invoke(activity, args);
					field.set(activity, obj);
					
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
