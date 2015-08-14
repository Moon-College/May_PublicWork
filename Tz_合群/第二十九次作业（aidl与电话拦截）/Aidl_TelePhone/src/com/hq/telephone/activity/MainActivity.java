package com.hq.telephone.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hq.telephone.service.MyService;
import com.hq.util.TelePhoneUtil;

public class MainActivity extends Activity {

	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void onclick(View view) {
		switch (view.getId()) {
		case R.id.displayCall:
			try {
				TelePhoneUtil.openCallScreen("110");
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case R.id.holdCall:
			// 启动电话拦截
			intent = new Intent();
			intent.setClass(this, MyService.class);
			this.startService(intent);
			break;
		case R.id.callPhone:
			break;
		default:
			break;
		}

	}
}
