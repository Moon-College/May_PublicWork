package com.example.lanuchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SingleTop extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.single_layout);

	}
	/**
	 * 按钮事件
	 * @param v
	 */
	public void onclick(View v) {
		switch (v.getId()) {
		case R.id.clickmy:
			// 再次打开自己
			Intent intentForMy = new Intent(SingleTop.this,SingleTop.class);
			startActivity(intentForMy);
			Toast.makeText(SingleTop.this, "又打开自己了", 0).show();
			break;
		case R.id.clicktask:
			// 打开独享栈
			Intent intentForTask = new Intent(SingleTop.this,SingleTask.class);
			startActivity(intentForTask);
			break;
		default:
			break;
		}
	}
}
