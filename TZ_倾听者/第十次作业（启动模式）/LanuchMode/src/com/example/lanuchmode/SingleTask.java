package com.example.lanuchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SingleTask extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.single_task);
	}

	public void onclick(View v) {
		// 打开第二个界面,让他再打开这个SingTask，看看会不会把之前的所有都清除
		Intent intent = new Intent(SingleTask.this, SingleTop.class);
		startActivity(intent);
	}
	public void oninstance(View v) {
		//打开SingleInstance
		Intent intent = new Intent(SingleTask.this,SingleInstance.class);
		startActivity(intent);
	}
}
