package com.junwen.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.intentdemo.R;

public class MainActivity extends Activity {

	private Intent intent;
	private Context context;
	//拨号页面的action
	private final String OPEN_PHONE = "com.junwen.actionPhone";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();
	}

	private void initView() {
		context = this;
	}

	public void onItemClick(View view) {
		intent = new Intent();
		switch (view.getId()) {
		case R.id.btn_takePhoto:
			// 拍照
			startIntent(TakePhotoActivity.class);
			break;
		case R.id.btn_takePhone:
			// 打电话
			startIntent(TakePhoneActivity.class);
			break;
		case R.id.btn_takeSms:
			// 发短信
			startIntent(TakeSmsActivity.class);
			break;
		case R.id.btn_takeEmail:
			// 发邮件
			startIntent(TakeEamilActivity.class);
			break;
		case R.id.btn_inflater:
			// 打开隐式意图
			//action
			intent.setAction(OPEN_PHONE);
			intent.addCategory(Intent.CATEGORY_DEFAULT);
			if( intent.resolveActivity(getPackageManager()) != null) {
				startActivity(intent);
			}
			break;
		default:
			break;
		}
	}
	/**
	 * 跳转页面
	 * @param context
	 * @param cls
	 */
	private void startIntent(Class<?> cls) {
		intent.setClass(context,cls);
		startActivity(intent);
	}
}
