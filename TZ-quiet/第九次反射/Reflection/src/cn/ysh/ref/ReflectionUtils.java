package cn.ysh.ref;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.util.Log;
import android.view.View;

public class ReflectionUtils {

	public static void initView(Object activity , String methodName){
		Class cls = activity.getClass();
		Field[] fields = cls.getDeclaredFields();
		for(Field field : fields){
			if(View.class.isAssignableFrom(field.getType())){
				try {
					Method method = cls.getMethod(methodName, new Class[]{int.class});
					Class cls1 = R.id.class;
					Field idField = cls1.getDeclaredField(field.getName());
					Object args = idField.getInt(idField);
					Object obj = method.invoke(activity, new Object[]{args});
					field.setAccessible(true);
					field.set(activity, obj);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
	}
}
