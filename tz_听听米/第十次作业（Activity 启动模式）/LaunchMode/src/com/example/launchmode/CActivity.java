package com.example.launchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.zoujm.utils.ReflectionUtil;

public class CActivity extends Activity implements OnClickListener {
	
	Button startDActivity,startAActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.clayout);
		super.onCreate(savedInstanceState);
		ReflectionUtil.initView(this, R.id.class);
		startDActivity.setOnClickListener(this);
		startAActivity.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.startDActivity:
			Intent intent = new Intent(CActivity.this,DActivity.class);
			startActivity(intent);
			break;
		case R.id.startAActivity:
			Intent intent1= new Intent(CActivity.this,MainActivity.class);
			startActivity(intent1);
			break;

		default:
			break;
		}
		
	}
	
}
