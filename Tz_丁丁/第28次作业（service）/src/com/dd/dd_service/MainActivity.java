package com.dd.dd_service;

import java.io.IOException;

import com.dd.dd_service.service.MyService;
import com.dd.dd_service.service.MyService.MyBinder;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity implements OnClickListener,ServiceConnection{
	private Button button5, button6, button7, button8;
	private MyService myService;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		Intent intent = new Intent();
		intent.setClass(this, MyService.class);
		this.startService(intent);//启动服务
		
		//绑定服务
		this.bindService(intent, this, Context.BIND_AUTO_CREATE);//在子线程执行
	}
	private void initView() {
		button5 = (Button) findViewById(R.id.button5);
		button6 = (Button) findViewById(R.id.button6);
		button7 = (Button) findViewById(R.id.button7);
		button8 = (Button) findViewById(R.id.button8);
		button5.setOnClickListener(this);
		button6.setOnClickListener(this);
		button7.setOnClickListener(this);
		button8.setOnClickListener(this);
		
	}
	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		MyService.MyBinder binder = (MyBinder) service;
		myService = binder.getMyService();//获取service对象
		myService.check();
	}
	@Override
	public void onServiceDisconnected(ComponentName name) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		intent.setClass(this, MyService.class);
		switch (v.getId()) {
		case R.id.button5:
			try {
				myService.playMusic();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.button6:
			myService.pauseMusic();
			break;
		case R.id.button7:
			myService.continuMusic();
			break;
		case R.id.button8:
			myService.stopMusic();
			break;
		default:
			break;
		}
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(myService!=null){
			this.unbindService(this);
		}
	}
}
