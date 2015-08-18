package com.tz.asy.common;


import android.app.Application;
import android.util.Log;
import com.tz.asy.http.HttpRequest;

public class MyApplication extends Application{
	public static HttpRequest request;
	public static MyApplication app;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i("INFO", "app oncreate");
		app = this;
	}
	
	
	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
	}
	
	//获取单例的application对象
	public static MyApplication getInstance(){
		if(app == null){
			app = new MyApplication();
		}
		return app;
	}
	//单例静态
	public static HttpRequest getHttpRequestInstance(){
		if(request == null){
			request = new HttpRequest(app.getApplicationContext());
		}
		return request;
	}
}
