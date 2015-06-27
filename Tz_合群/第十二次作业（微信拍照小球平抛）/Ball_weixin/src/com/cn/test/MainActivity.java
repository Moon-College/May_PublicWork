package com.cn.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.cn.test.utils.Reflection;

public class MainActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	private Button bt1, bt2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Reflection.initView(this);
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt1:
			Intent weixin = new Intent(MainActivity.this, WeixinActivity.class);
			startActivity(weixin);
			break;
		case R.id.bt2:
			Intent ball = new Intent(MainActivity.this, BallActivity.class);
			startActivity(ball);
			break;
		default:
			break;
		}

	}

}