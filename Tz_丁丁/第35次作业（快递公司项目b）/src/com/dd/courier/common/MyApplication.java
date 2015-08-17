package com.dd.courier.common;


import com.dd.courier.http.HttpRequest;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {
	public static HttpRequest request;
	public static MyApplication app;
	@Override
	public void onCreate() {
		super.onCreate();
		Log.e("home", "app oncreate");
		app = this;
	}
	
	
	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
		Log.e("home", "app onTerminate");
	}
	
	//获取单例的application对象
	public static MyApplication getInstance(){
		Log.e("home", "getInstance");
		if(app == null){
			app = new MyApplication();
		}
		return app;
	}
	//单例静态
	public static HttpRequest getHttpRequestInstance(){
		Log.e("home", "getHttpRequestInstance");
		if(request == null){
			request = new HttpRequest(app.getApplicationContext());
		}
		return request;
	}
}
