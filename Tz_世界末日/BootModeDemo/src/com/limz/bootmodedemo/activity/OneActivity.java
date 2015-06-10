package com.limz.bootmodedemo.activity;

import com.limz.bootmodedemo.constant.MyConstant;
import com.limz.bootmodedemo.manage.ActivityStackManager;
import com.limz.bootmodedemo.utils.InitData;
import com.limz.bootmodedemo.utils.Utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class OneActivity extends Activity implements OnClickListener {

	private Button one_btn, lookone_btn, one_main_btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.one);
		init();
		setLinster();
	}

	private void init() {
		ActivityStackManager manager = ActivityStackManager.getInstance(this);
		manager.addActivity(this);
		InitData.init(this);		
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ActivityStackManager manager = ActivityStackManager.getInstance(this);
		manager.removeActivityList(this);
	}

	private void setLinster() {
		if(one_btn == null ||
			lookone_btn == null) {
			
			Log.e("mingzhu", "the button not init");
			return;
		}
		one_btn.setOnClickListener(this);
		lookone_btn.setOnClickListener(this);
		one_main_btn.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.one_btn:
				Intent intent = new Intent();
				intent.setAction(MyConstant.ACTIVITYACTIONONE);
				startActivity(intent);
				break;
				
			case R.id.lookone_btn:
				Utils.showActivityToast(this);
				break;
				
			case R.id.one_main_btn:
				Intent intent2 = new Intent();
				intent2.setClass(this, BootModeDemoActivity.class);
				startActivity(intent2);
				break;
	
			default:
				break;
		}
	}
}
