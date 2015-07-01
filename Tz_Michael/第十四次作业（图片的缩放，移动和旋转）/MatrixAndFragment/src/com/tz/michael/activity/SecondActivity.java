package com.tz.michael.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class SecondActivity extends FragmentActivity {

	final String TAG="SecondActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("TAG", "activity---onCreate");
		setContentView(R.layout.second_ac);
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.i("TAG", "activity---onStart");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.i("TAG", "activity---onResume");
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		Log.i("TAG", "activity---onPause");
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		Log.i("TAG", "activity---onStop");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i("TAG", "activity---onDestroy");
	}

	
	@Override
	public View onCreateView(String name, Context context, AttributeSet attrs) {
		// TODO Auto-generated method stub
		return super.onCreateView(name, context, attrs);
	}

}
