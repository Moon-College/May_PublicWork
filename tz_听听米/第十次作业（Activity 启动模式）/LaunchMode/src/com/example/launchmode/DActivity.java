package com.example.launchmode;

import com.zoujm.utils.ReflectionUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DActivity extends Activity implements OnClickListener {
	Button addCount,startBActivity,startDActivity;
	int count;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.dlayout);
		super.onCreate(savedInstanceState);
		ReflectionUtil.initView(this, R.id.class);
		addCount.setOnClickListener(this);
		startBActivity.setOnClickListener(this);
		startDActivity.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.addCount:
			count++;
			addCount.setText(String.valueOf(count));
			break;
		case R.id.startBActivity:
			Intent intent = new Intent(DActivity.this,BActivity.class);
			startActivity(intent);
			break;
		case R.id.startDActivity:
			Intent intent1 = new Intent(DActivity.this,DActivity.class);
			startActivity(intent1);
			break;

		default:
			break;
		}
		
	}

}
