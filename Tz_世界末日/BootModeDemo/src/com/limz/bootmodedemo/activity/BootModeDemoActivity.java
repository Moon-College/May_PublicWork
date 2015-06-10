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

public class BootModeDemoActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	private Button standared_btn, singletop_btn, singletask_btn, singleinstance_btn, look_btn;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initData();
        setLinster();
    }

	private void setLinster() {
		if(standared_btn == null ||
			singletop_btn == null ||
			singletask_btn == null ||
			singleinstance_btn == null ||
			look_btn == null) {
			
			Log.e("mingzhu", "the button not init");
			return;
		}
		standared_btn.setOnClickListener(this);
		singletop_btn.setOnClickListener(this);
		singletask_btn.setOnClickListener(this);
		singleinstance_btn.setOnClickListener(this);
		look_btn.setOnClickListener(this);
	}

	private void initData() {
        ActivityStackManager manager = ActivityStackManager.getInstance(this);
        manager.addActivity(this);
		InitData.init(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
	
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
			case R.id.standared_btn:
				intent.setAction(MyConstant.ACTIVITYACTIONONE);
				startActivity(intent);
				break;
				
			case R.id.singletop_btn:
				intent.setAction(MyConstant.ACTIVITYACTIONTWO);
				startActivity(intent);
				break;
							
			case R.id.singletask_btn:
				intent.setAction(MyConstant.ACTIVITYACTIONTHREE);
				startActivity(intent);
				break;
				
			case R.id.singleinstance_btn:
				intent.setClass(this, FourActivity.class);
				startActivity(intent);
				break;
				
			case R.id.look_btn:
				Utils.showActivityToast(this);
				break;
	
			default:
				break;
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ActivityStackManager manager = ActivityStackManager.getInstance(this);
		manager.removeActivityList(this);
	}
}