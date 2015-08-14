package com.junwen.courier.activity;

import com.junwen.courier.R;
import com.junwen.courier.common.BaseActivity;
import com.junwen.util.Logs;


public class SplashActivity extends BaseActivity {
	
	
	/**
	 * 初始化视图
	 */
	@Override
	public void initContentView() {
		setContentView(R.layout.splash_layout);
		Logs.I("initContentView");
	}
	
	/**
	 * 初始化控件
	 */
	@Override
	public void initView() {
		Logs.I("initView");
	}
	
	/**
	 * 初始化监听
	 */
	@Override
	public void initListener() {
		Logs.I("initListener");
	}
	
	/**
	 * 初始化数据
	 */
	@Override
	public void initData() {
		Logs.I("initData");
	}

}
