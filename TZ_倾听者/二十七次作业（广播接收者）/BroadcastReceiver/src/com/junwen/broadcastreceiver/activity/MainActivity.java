package com.junwen.broadcastreceiver.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.broadcastreceiver.R;
import com.junwen.broadcastreceiver.reveiver.BroadcastReveiver;
import com.junwen.broadcastreceiver.reveiver.BroadcastReveiver.OnReveiver;
import com.junwen.broadcastreceiver.reveiver.LockScreenBroadcastReveiver;

public class MainActivity extends Activity implements OnReveiver {
	
	private BroadcastReveiver broReveiver;
	private LockScreenBroadcastReveiver lock;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}
	/**
	 * 初始化
	 */
	private void initView() {
		broReveiver  = new BroadcastReveiver();
		lock = new LockScreenBroadcastReveiver();
		broReveiver.setOnReveiverListener(this);
		//注册
		registerReceiver(lock,"android.intent.action.SCREEN_OFF",1000);
	}

	public void onclick(View v) {
		switch (v.getId()) {
		case R.id.start_receiver:
			//开启有序广播
			startBroadcast();
			break;
		case R.id.over_receiver:
			//中断广播
			overBroadcast(broReveiver);
			break;
		case R.id.regist_receiver:
			//注册广播
			registerReceiver(broReveiver,"com.junwen.reveiver",1000);
			break;
		default:
			break;
		}
	}
	/**
	 * 开启有序广播
	 */
	private void startBroadcast() {
		Intent intent = new Intent();
//		intent.setClass(this, BroadcastReveiver.class);
		intent.setAction("com.junwen.reveiver");
		this.sendOrderedBroadcast(intent, null);
	}
	/**
	 * 注册广播
	 */
	private void registerReceiver(BroadcastReceiver broadcastReceiver,String action,int priority) {
			IntentFilter filter = new IntentFilter();
			filter.setPriority(priority);
			filter.addAction(action);
			this.registerReceiver(broadcastReceiver, filter);
			System.out.println("dd");
	}

	/**
	 * 关闭广播
	 */
	private void overBroadcast(BroadcastReceiver receiver) {
		//这里应该判断是否有注册，如果没有注册在这里又解除注册的话，会出现崩溃，
		//所以加上了try catch。
		if(receiver!=null){
			try {
				this.unregisterReceiver(receiver);
			} catch (Exception e) {
			}
		}
	}
	/**
	 * 退出时
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		overBroadcast(broReveiver);
	}
	/**
	 * 当接收到有序广播的时候,进行更新文本
	 */
	@Override
	public void onReveiver(Intent intent) {
		if(broReveiver!=null){
			Toast.makeText(MainActivity.this, "收到广播", 0).show();
		}
	}
}
