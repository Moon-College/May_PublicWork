package com.zjm.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;


public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i("INFO", "Activity onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
	
	@Override
	protected void onStart() {
		Log.i("INFO", "Activity onStart");
		super.onStart();
	}
	
	@Override
	protected void onResume() {
		Log.i("INFO", "Activity onResume");
		super.onResume();
	}

	@Override
	protected void onPause() {
		Log.i("INFO", "Activity onPause");
		super.onPause();
	}

	@Override
	protected void onStop() {
		Log.i("INFO", "Activity onStop");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		Log.i("INFO", "Activity onDestroy");
		super.onDestroy();
	}
	
	
}
