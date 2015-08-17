package com.dd.courier.common;


import com.dd.courier.http.HttpRequest;
import com.dd.courier.utils.MyLog;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public abstract class BaseActivity extends FragmentActivity{
	//申明所有activity都需要共同访问的对象
	MyApplication app;
	public HttpRequest request;
	//互联网请求的核心类的对象
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.e("home", "BaseActivity");
		app = MyApplication.getInstance();
		request = MyApplication.getHttpRequestInstance();
		MyLog.DEBUG = true;
		initContentView();
		initView();
		initListener();
		initData();
	}
	
	public abstract void initContentView();
	
	public abstract void initView();
	
	public abstract void initListener();
	
	public abstract void initData();
}
