package com.dd.courier.common;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		Log.i("home", "app oncreate");
	}
	
	
	@Override
	public void onTerminate() {
		super.onTerminate();
	}
}
