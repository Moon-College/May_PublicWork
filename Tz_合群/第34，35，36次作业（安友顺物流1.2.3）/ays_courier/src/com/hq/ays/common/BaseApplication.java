package com.hq.ays.common;

import com.hq.ays.http.HttpRequest;

import android.app.Application;

public class BaseApplication extends Application{
	public static BaseApplication app;
	public static HttpRequest request;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		app = this;
	}
	
	
	public static BaseApplication getInstance(){
		return app;
	}
	
	public static HttpRequest initHttpRequest(){
		if(request == null){
			request = new HttpRequest(app.getApplicationContext());
		}
		return request;
	}
}
