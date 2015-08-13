package com.decent.courier.common;

import com.decent.courier.http.HttpRequest;

import android.app.Application;

public class DecentApplication extends Application {
	private static DecentApplication app;
	private static HttpRequest httpRequest;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		app = this;
	}

	public static DecentApplication getInstance() {
		// TODO Auto-generated method stub
		if(app == null){
			app = new DecentApplication();
		}
		return app;
	}

	public static HttpRequest getHttpRequestInstance() {
		// TODO Auto-generated method stub
		if(httpRequest == null){
			httpRequest = new HttpRequest(app.getApplicationContext());
		}
		return httpRequest;
	}
	
}
