package com.decent.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.decent.broadcast.receiver.DecentSrceenOffBroadcastReceiver;
import com.decent.broadcast.receiver.SecondClassBroadcastReceiver;
import com.decent.decentutil.ReflictionUtil;

public class DecentBroadcastActivity extends Activity implements
		OnClickListener {
	private Button textOrderedBtn;
	private Button textAbortBtn;
	private DecentSrceenOffBroadcastReceiver screenOffReceiver;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ReflictionUtil.InjectionView(DecentBroadcastActivity.class.getName(),
				R.class.getName(), this);
		textOrderedBtn.setOnClickListener(this);
		textAbortBtn.setOnClickListener(this);
		
		screenOffReceiver = new DecentSrceenOffBroadcastReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		this.registerReceiver(screenOffReceiver, filter);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.textOrderedBtn:
			Intent orderedIntent = new Intent();
			orderedIntent.setAction("decent.stock.rose");
			this.sendOrderedBroadcast(orderedIntent, null);
			break;
		case R.id.textAbortBtn:
			Intent abortIntent = new Intent();
			abortIntent.setAction("decent.stock.rose");
			SecondClassBroadcastReceiver.setReadyToAbort(true);
			this.sendOrderedBroadcast(abortIntent, null);			
			break;
		default:			
			break;
		}
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		this.unregisterReceiver(screenOffReceiver);
	}
}