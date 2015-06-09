package com.cn.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

/**
 * Created on2015-6-9 ÏÂÎç2:53:11 FirstActivity.java<br/>
 * Author ZhuHequn 1363790125@qq.com <br/>
 * version 1.0 TODO
 */
public class FirstActivity extends Activity implements OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		Log.d("FirstActivity", this.toString());
		Log.d("FirstActivity", "Task id is " + getTaskId());
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.first_layout);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		Button bt=(Button) findViewById(R.id.button_1);
		bt.setOnClickListener(this);
		
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
//		Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
//		intent.putExtra("param1", "data1");
//		intent.putExtra("param2", "data2");
//		startActivity(intent);
		SecondActivity.actionStart(FirstActivity.this, "data1", "data2");
	}	

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d("FirstActivity", "onRestart");
	}

	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 1:
			if (resultCode == RESULT_OK) {
				String returnedData = data.getStringExtra("data_return");
				Log.d("FirstActivity", returnedData);
			}
			break;
		default:
		}
	}
}
