package com.example.launchmode;

import com.zoujm.utils.ReflectionUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BActivity extends Activity implements OnClickListener{
	
	Button startCActivity, startBActivity,startAActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.blayout);
		super.onCreate(savedInstanceState);
		ReflectionUtil.initView(this, R.id.class);
		startCActivity.setOnClickListener(this);
		startBActivity.setOnClickListener(this);
		startAActivity.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.startCActivity:
			Intent intent = new Intent(BActivity.this,CActivity.class);
			startActivity(intent);
			break;
		case R.id.startBActivity:
			Intent intent1 = new Intent(BActivity.this,BActivity.class);
			startActivity(intent1);
			break;
		case R.id.startAActivity:
			Intent intent2 = new Intent(BActivity.this,MainActivity.class);
			startActivity(intent2);
			break;

		default:
			break;
		}
		
	}

}
