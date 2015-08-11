package com.decent.courier.common;

import android.app.Activity;
import android.os.Bundle;

/**
 * ������Activity�࣬
 * 1����Activity��onCreate����ֳ���4���׶Ρ�����Ӧ4������
 * 2��Ϊÿһ��Activity�����ֶ�DecentApplication������Activity֮�乲�����ݣ���������
 * @author K450J
 *
 */
public abstract class BaseActivity extends Activity {
	/**
	 * Ϊÿһ��Activity�����ֶ�DecentApplication������Activity֮�乲�����ݣ���������
	 */
	private DecentApplication mDecentApplication;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
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
