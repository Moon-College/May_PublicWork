/**
 * Project Name:lsn29_service
 * File Name:MyIntentService.java
 * Package Name:com.zht.service
 * Date:2015-7-26下午9:27:42
 * Copyright (c) 2015, hongtao8@staff.sina.com.cn All Rights Reserved.
 *
 */

package com.zht.service;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * ClassName:MyIntentService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-7-26 下午9:27:42 <br/>
 * 
 * @author hongtao
 * @version
 * @since JDK 1.6
 * @see
 */
public class MyIntentService extends IntentService {
	/**
	 * IntentService: onHandleIntent是在子线程中执行耗时操作，完成之后会停止
	 */
	/**
	 * Service: 在主线程执行操作，操作完成不会停止
	 */
	private MediaPlayer mp;
	private String str;
	
    //构造方法注意String name
	public MyIntentService() {
		super(MyIntentService.class.getName());

	}

	@Override
	public void onCreate() {
		super.onCreate();
		mp = new MediaPlayer();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Log.i("INFO", "onStart");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("INFO", "intentService destroy");
	}

	@Override
	public IBinder onBind(Intent intent) {
		return new MyIntentBinde();
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.i("INFO", "onHandleIntent");
		str = new String();
		int i = 0;
		while (i < 100000000) {
			if(i == 100000000-1){
				str = String.valueOf(i);
			}
			i++;
		}
	}

	public String getCount() {
		return str;
	}

	class MyIntentBinde extends Binder {
		public MyIntentService getIntentService() {
			return MyIntentService.this;
		}
	}
}
