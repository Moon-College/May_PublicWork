package com.example.lanuchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Standard extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}
	public void onclick(View v) {
		Intent intent = new Intent();
		intent.setClass(Standard.this, SingleTop.class);
		startActivity(intent);
	}
}
