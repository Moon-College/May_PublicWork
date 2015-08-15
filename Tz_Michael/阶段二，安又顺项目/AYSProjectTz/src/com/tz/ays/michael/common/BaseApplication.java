package com.tz.ays.michael.common;

import com.tz.ays.michael.http.HttpRequest;

import android.app.Application;

public class BaseApplication extends Application {

	/**共用常量的单例模式*/
	private static BaseApplication app;
	private static HttpRequest httpRequest;
	
	public static BaseApplication getInstance(){
		return app;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		app=this;
	}
	
	public static HttpRequest initReQuest(){
		if(httpRequest==null){
			httpRequest=new HttpRequest(app.getApplicationContext());
		}
		return httpRequest;
	}
	
}
