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
	 * ��ť�¼�
	 * @param v
	 */
	public void onclick(View v) {
		switch (v.getId()) {
		case R.id.clickmy:
			// �ٴδ��Լ�
			Intent intentForMy = new Intent(SingleTop.this,SingleTop.class);
			startActivity(intentForMy);
			Toast.makeText(SingleTop.this, "�ִ��Լ���", 0).show();
			break;
		case R.id.clicktask:
			// �򿪶���ջ
			Intent intentForTask = new Intent(SingleTop.this,SingleTask.class);
			startActivity(intentForTask);
			break;
		default:
			break;
		}
	}
}
