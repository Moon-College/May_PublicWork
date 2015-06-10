package com.example.lanuchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SingleInstance extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.singinstance_layout);
	}
	public void onclick(View v) {
		Intent intent = new Intent(SingleInstance.this,Standard.class);
		startActivity(intent);
	}
}
