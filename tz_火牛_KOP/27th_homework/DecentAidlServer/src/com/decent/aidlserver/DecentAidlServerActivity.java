package com.decent.aidlserver;

import com.decent.aidlserver.service.DecentAidlServie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DecentAidlServerActivity extends Activity implements
		OnClickListener {
	private Button startServiceBtn;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		startServiceBtn = (Button) findViewById(R.id.startServiceBtn);
		startServiceBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.startServiceBtn:
            Intent intent=new Intent();
            intent.setClass(this, DecentAidlServie.class);
			startService(intent);
			break;

		default:
			break;
		}
	}
}