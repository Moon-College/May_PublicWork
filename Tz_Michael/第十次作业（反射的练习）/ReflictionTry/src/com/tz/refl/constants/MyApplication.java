package com.tz.refl.constants;

import android.app.Application;
import android.graphics.Typeface;

public class MyApplication extends Application {

	public static Typeface typeFace;;
	private static MyApplication instance;
	
	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		typeFace =Typeface.createFromAsset(getAssets(),"fonts/MicrosoftYaHeiGB.ttf");
	}
	
	/**
	 * 返回application的一个实例
	 * @return
	 */
	public static MyApplication getInstance() {
		return instance;
	}
	
}
