package com.zht.activity_cycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class OtherActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.other);
		System.out.println("OtherActivity onCreate");
	}

	@Override
	protected void onStart() {
		super.onStart();
		System.out.println("OtherActivity onStart");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		System.out.println("OtherActivity onRestart");
	}

	@Override
	protected void onPause() {
		super.onPause();
		System.out.println("OtherActivity onPause");
	}

	@Override
	protected void onStop() {
		super.onStop();
		System.out.println("OtherActivity onStop");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.out.println("OtherActivity onDestroy");
	}

	@Override
	protected void onResume() {
		super.onResume();
		System.out.println("OtherActivity onResume");
	}
	

}
