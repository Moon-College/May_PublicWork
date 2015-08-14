package com.decent.courier.common;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.decent.courier.http.HttpRequest;

/**
 * ������Activity�࣬
 * 1����Activity��onCreate����ֳ���4���׶Ρ�����Ӧ4������
 * 2��Ϊÿһ��Activity�����ֶ�DecentApplication������Activity֮�乲�����ݣ���������
 * @author K450J
 *
 */
public abstract class BaseActivity extends FragmentActivity {
	/**
	 * Ϊÿһ��Activity�����ֶ�DecentApplication������Activity֮�乲�����ݣ���������
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
	 * �������Activity��ʾ��view
	 */
	public abstract void initContentView();
	/**
	 * ��ʼ�������ؼ���view
	 */
	public abstract void initView();
	/**
	 * ��ʼ������Listener
	 */
	public abstract void initListener();
	/**
	 * ��ʼ������
	 */
	public abstract void initData();



}
