package com.qfx.SingleInstance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
	
	public void onClick(View v) {
		Intent intent = new Intent();
		intent.setAction("com.qfx.launchermode.SECONDACTIVITY");
		startActivity(intent);
	}
}
