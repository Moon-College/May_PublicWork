package com.dd.courier.common;

import android.app.Activity;
import android.os.Bundle;

public abstract class BaseActivity extends Activity{
	//申明所有activity都需要共同访问的对象
	MyApplication app;
	//互联网请求的核心类的对象
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
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
