package com.qfx.launchermode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FirstActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
	}
	
	public void onClick(View v) {
		Intent intent = new Intent();
		intent.setClass(this, SecondActivity.class);
		startActivity(intent);
	}
}
