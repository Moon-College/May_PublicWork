/**
 * Project Name:lsn29_service
 * File Name:MainActivity.java
 * Package Name:com.zht.service
 * Date:2015-7-26下午5:21:21
 * Copyright (c) 2015, hongtao8@staff.sina.com.cn All Rights Reserved.
 *
 */

package com.zht.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zht.service.MusicService.MyBinder;
import com.zht.service.MyIntentService.MyIntentBinde;

/**
 * ClassName:MainActivity <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-7-26 下午5:21:21 <br/>
 * 
 * @author hongtao
 * @version
 * @since JDK 1.6
 * @see
 */
public class MainActivity extends Activity {
	private Intent intent1;
	private Intent intent2;
	public MusicService mService;
	public MyIntentService mIntentService;
	private MyServiceConnection conn;
	private MyIntentServiceConnection InConn;
	private TextView tv_num;


	class MyServiceConnection implements ServiceConnection {

		public void onServiceConnected(ComponentName name, IBinder service) {
			// 绑定完成以后回调的方法（Activity和service的连接）
			mService = ((MyBinder) service).getMusicService();
		}

		public void onServiceDisconnected(ComponentName name) {
			// 当服务解绑的时候回调的方法。

		}

	}

	class MyIntentServiceConnection implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mIntentService = ((MyIntentBinde) service).getIntentService();

		}

		@Override
		public void onServiceDisconnected(ComponentName name) {

		}

	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		tv_num = (TextView) findViewById(R.id.num);

		intent1 = new Intent("com.zht.service.mp3.MusicService");
		intent2 = new Intent("com.zht.service.is.IntentService");
		conn = new MyServiceConnection();
		InConn = new MyIntentServiceConnection();
		startService(intent1);
		bindService(intent1, conn, Context.BIND_AUTO_CREATE);
		startService(intent2);
		bindService(intent2, InConn, Context.BIND_AUTO_CREATE);
	}

	public void btn(View v) {
		switch (v.getId()) {
		case R.id.start:
			mService.doStart();
			break;
		case R.id.stop:
			mService.doStop();
			break;
		case R.id.pause:
			mService.doPause();
			break;
		case R.id.resume:
			mService.doResume();
			break;
		case R.id.count:
			tv_num.setText(mIntentService.getCount());
			break;
		default:
			break;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mService != null) {
			unbindService(conn);
		}
	}
}
