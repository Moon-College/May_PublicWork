package com.hq.ays.common;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.hq.ays.http.HttpRequest;
import com.hq.ays.utils.MyLog;

public abstract class BaseActivity extends FragmentActivity{
	public BaseApplication app;
	public HttpRequest request;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		app = BaseApplication.getInstance();
		request = BaseApplication.initHttpRequest();
		MyLog.DEBUG = true;
		setContentView();
		initViews();
		initListeners();
		initData();
	}
	
	public abstract void setContentView();
	
	public abstract void initViews();
	
	public abstract void initListeners();
	
	public abstract void initData();
	
}
