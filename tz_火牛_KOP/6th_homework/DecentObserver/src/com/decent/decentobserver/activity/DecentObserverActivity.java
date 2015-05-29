package com.decent.decentobserver.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.decent.decentobserver.DecentAbstractObserver;
import com.decent.decentobserver.DecentNewsObserver;
import com.decent.decentobserver.R;
import com.decent.observable.DecentAbstractObservable;
import com.decent.observable.DecentNewsObserable;

public class DecentObserverActivity extends Activity implements OnClickListener
{
	private EditText mEt;
	private Button mBt;
	private DecentAbstractObservable mDNO;
	private DecentAbstractObserver mDAO1;
	private DecentAbstractObserver mDAO2;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		/*
		 * 初始化被观察者和观察者并且注册
		 */
		mDNO = new DecentNewsObserable();
		mDAO1 = new DecentNewsObserver(this);
		mDAO2 = new DecentNewsObserver(this);
		mDNO.addDecentObserver(mDAO1);
		mDNO.addDecentObserver(mDAO2);
		/*
		 * 获取控件，注册监听函数
		 */
		mEt = (EditText) findViewById(R.id.et);
		mBt = (Button) findViewById(R.id.bt);
		mBt.setOnClickListener(this);
	}

	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		int value = Integer.valueOf(mEt.getText().toString().trim());
		Log.d("DecentObserverActivity", "value=" + value);
		// if(value == 3)
		// {
		String args[] =
		{ String.valueOf(value) };
		mDNO.notifyAllDecentObserver(args);
		// }
	}
}