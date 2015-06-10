package com.example.launchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.zoujm.utils.ReflectionUtil;

public class MainActivity extends Activity implements OnClickListener {
	
	Button startBActivity,addCount;
	int count;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ReflectionUtil.initView(this, R.id.class);
		startBActivity.setOnClickListener(this);
		addCount.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.startBActivity:
			Intent intent = new Intent(MainActivity.this,BActivity.class);
			startActivity(intent);
			break;
		case R.id.addCount:
			count++;
			startBActivity.setText(String.valueOf(count));
			break;

		default:
			break;
		}
		
	}

	
}
