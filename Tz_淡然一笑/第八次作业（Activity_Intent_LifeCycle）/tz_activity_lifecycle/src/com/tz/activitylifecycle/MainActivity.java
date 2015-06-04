package com.tz.activitylifecycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private Button btn_normal;
	private Button btn_dialog;

	// 创建
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i("INFO", "onCreate");

		btn_normal = (Button) findViewById(R.id.btn_normal);
		btn_dialog = (Button) findViewById(R.id.btn_dialog);

		btn_normal.setOnClickListener(this);
		btn_dialog.setOnClickListener(this);
	}

	// 开启
	// 当Activity全部可见了以后，才会形成视图
	@Override
	protected void onStart() {
		super.onStart();
		Log.i("INFO", "onStart");
	}

	// 获取到了焦点，并且对它可以进行操作
	@Override
	protected void onResume() {
		super.onResume();
		Log.i("INFO", "onResume");
	}

	// 暂停，当前界面被部分遮挡
	// 轩儿来短信的时候，显示部分短信界面
	@Override
	protected void onPause() {
		super.onPause();
		Log.i("INFO", "onPause");
	}

	// 停止，当前界面被全部遮住
	// 轩儿来电话的时候，显示电话的界面
	@Override
	protected void onStop() {
		super.onStop();
		Log.i("INFO", "onStop");
	}

	// 销毁
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i("INFO", "onDestroy");
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i("INFO", "onRestart");
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.btn_normal:
			intent = new Intent(this,NoramlActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_dialog:
			intent = new Intent(this,DialogActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}

	}
}
