package com.tz.ays.michael.common;

import com.tz.ays.michael.http.HttpRequest;
import com.tz.ays.michael.utils.Check;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
/**
 * 模板activity
 * @author michael
 *
 */
public abstract class BaseActivity extends FragmentActivity {

	public BaseApplication baseApp;
	public HttpRequest hRequest;
	public Context mContext;
	public Check check;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		baseApp=BaseApplication.getInstance();
		hRequest=BaseApplication.initReQuest();
		mContext=this;
		check=new Check(mContext);
		setContentLayout();
		initViews();
		setListenners();
		getData();
	}
	
	/**
	 * 设置主布局
	 */
	public abstract void setContentLayout();
	/**
	 * 实例化各种控件
	 */
	public abstract void initViews();
	/**
	 * 设置监听器
	 */
	public abstract void setListenners();
	/**
	 * 获取数据
	 */
	public abstract void getData();
	
}
