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

public class ThreeActivity extends Activity implements OnClickListener {
	
	private Button three_btn, lookthree_btn, three_main_btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.three);
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
		if(three_btn == null ||
				lookthree_btn == null ||
				three_main_btn == null) {
			
			Log.e("mingzhu", "the button not init");
			return;
		}
		three_btn.setOnClickListener(this);
		lookthree_btn.setOnClickListener(this);
		three_main_btn.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.three_btn:
				Intent intent = new Intent();
				intent.setAction(MyConstant.ACTIVITYACTIONTHREE);
				startActivity(intent);
				break;
				
			case R.id.lookthree_btn:
				Utils.showActivityToast(this);
				break;
	
			case R.id.three_main_btn:
				Intent intent2 = new Intent();
				intent2.setClass(this, BootModeDemoActivity.class);
				startActivity(intent2);
				break;
			default:
				break;
		}
	}
}
