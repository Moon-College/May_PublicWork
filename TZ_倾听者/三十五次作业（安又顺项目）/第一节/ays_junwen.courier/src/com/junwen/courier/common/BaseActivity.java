package com.junwen.courier.common;

import android.app.Activity;
import android.os.Bundle;

public abstract class BaseActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
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
