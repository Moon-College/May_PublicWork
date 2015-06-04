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

	// ����
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

	// ����
	// ��Activityȫ���ɼ����Ժ󣬲Ż��γ���ͼ
	@Override
	protected void onStart() {
		super.onStart();
		Log.i("INFO", "onStart");
	}

	// ��ȡ���˽��㣬���Ҷ������Խ��в���
	@Override
	protected void onResume() {
		super.onResume();
		Log.i("INFO", "onResume");
	}

	// ��ͣ����ǰ���汻�����ڵ�
	// ���������ŵ�ʱ����ʾ���ֶ��Ž���
	@Override
	protected void onPause() {
		super.onPause();
		Log.i("INFO", "onPause");
	}

	// ֹͣ����ǰ���汻ȫ����ס
	// �������绰��ʱ����ʾ�绰�Ľ���
	@Override
	protected void onStop() {
		super.onStop();
		Log.i("INFO", "onStop");
	}

	// ����
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
