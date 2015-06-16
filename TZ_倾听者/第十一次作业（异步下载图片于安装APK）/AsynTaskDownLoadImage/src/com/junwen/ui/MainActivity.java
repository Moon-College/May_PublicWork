package com.junwen.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.asyntaskdownloadimage.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void onclick(View v) {
		switch (v.getId()) {
		case R.id.downApk:
			// œ¬‘ÿAPK
			Intent intent1 = new Intent(MainActivity.this,DownApkForAsynTask.class);
			startActivity(intent1);
			break;

		case R.id.downImage:
			// œ¬‘ÿÕº∆¨
			Intent intent2 = new Intent(MainActivity.this,DownImageForAsynTask.class);
			startActivity(intent2);
			break;
		default:
			break;
		}
	}
}
