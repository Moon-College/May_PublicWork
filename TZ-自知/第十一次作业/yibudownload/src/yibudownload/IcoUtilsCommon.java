package yibudownload;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.example.yibudownload.R;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

public class IcoUtilsCommon {

	public static void Doinit(final Activity activity) {
		try {
			//获取累模板
			Class classes = activity.getClass();
			
			Field[] fields = classes.getDeclaredFields();
			for (Field field : fields) {
				InjectionName anntion =field.getAnnotation(InjectionName.class);
				if (anntion != null ) {
					//打破封装
					field.setAccessible(true);
					//获取findViewById方法
					Method findId = classes.getMethod("findViewById", new Class[] { int.class });
					//获取R文件的从的Id模板
					Class classid = R.id.class;
					//获取这个变量
					Field theid = classid.getDeclaredField(field.getName());
					//获取变量的id
					Object getid = theid.getInt(theid);
					//找到这个变量的id地址
					Object value = findId.invoke(activity, new Object[]{getid});
					//给变量赋值
					field.set(activity, value);

					final String vlauesmeath=anntion.values();
					System.out.println("Qwe111");
					if( !vlauesmeath.equals(""))
					{
						final Method myshow = classes.getDeclaredMethod(vlauesmeath, new Class[]{});
						if ( myshow != null) {
							final View myView = activity.findViewById((Integer) getid);
							myView.setOnClickListener(new OnClickListener() {
								@Override
								public void onClick(View v) {
									try {
										myshow.invoke(activity, null); 
									} catch (Exception e) {
										System.out.println("Qwe111"+e.toString());
									}
								}
							});
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("q "+e.toString());
		}
	}

}
