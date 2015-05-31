package com.zjm.observer.custom;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * @author 听听米
 * 2015-5-31
 */
public class MyObserver implements BaseObserver {

	@Override
	public void update(BaseObservable baseObservable, Object data) {
		System.out.println("观察者观察到的数据:"+data);
		Log.d("TAG", "观察者观察到的数据:"+data);
	}
	
	/**
	 * 
	 * @author 听听米
	 * @data 2015-5-31下午4:14:39
	 * @belong com.zjm.observer.custom.MyObserver
	 * @describe：
	 * @param context
	 * @param data
	 * @return void
	 */
	public void insert(Context context,Object data){
		System.out.println("观察者插入数据:"+data);
		Toast.makeText(context, "观察者插入数据:"+data, 1000).show();
	}
	
	

}
