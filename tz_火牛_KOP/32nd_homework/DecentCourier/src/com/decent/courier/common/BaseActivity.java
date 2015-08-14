package com.decent.courier.common;

import android.app.Activity;
import android.os.Bundle;

import com.decent.courier.http.HttpRequest;

/**
 * 基本的Activity类，
 * 1、把Activity的onCreate里面分成了4个阶段——对应4个函数
 * 2、为每一个Activity增加字段DecentApplication，用来Activity之间共享数据，缓存数据
 * @author K450J
 *
 */
public abstract class BaseActivity extends Activity {
	/**
	 * 为每一个Activity增加字段DecentApplication，用来Activity之间共享数据，缓存数据
	 */
	public DecentApplication mDecentApplication;
	public HttpRequest mHttpRequest;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mDecentApplication = DecentApplication.getInstance();
		mHttpRequest = DecentApplication.getHttpRequestInstance();		
		initContentView();
		initView();
		initListener();
		initData();
	}
	/**
	 * 设置这个Activity显示的view
	 */
	public abstract void initContentView();
	/**
	 * 初始化各个控件的view
	 */
	public abstract void initView();
	/**
	 * 初始化各种Listener
	 */
	public abstract void initListener();
	/**
	 * 初始化数据
	 */
	public abstract void initData();



}
