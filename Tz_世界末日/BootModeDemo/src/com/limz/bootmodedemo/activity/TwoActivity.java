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

public class TwoActivity extends Activity implements OnClickListener {
	
	private Button two_btn, looktwo_btn, two_main_btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.two);
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
		if(two_btn == null ||
				looktwo_btn == null ||
				two_main_btn == null) {
			
			Log.e("mingzhu", "the button not init");
			return;
		}
		two_btn.setOnClickListener(this);
		looktwo_btn.setOnClickListener(this);
		two_main_btn.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.two_btn:
				Intent intent = new Intent();
				intent.setAction(MyConstant.ACTIVITYACTIONTWO);
				startActivity(intent);
				break;
				
			case R.id.looktwo_btn:
				Utils.showActivityToast(this);
				break;
	
			case R.id.two_main_btn:
				Intent intent2 = new Intent();
				intent2.setClass(this, BootModeDemoActivity.class);
				startActivity(intent2);
				break;
			default:
				break;
		}
	}
}
