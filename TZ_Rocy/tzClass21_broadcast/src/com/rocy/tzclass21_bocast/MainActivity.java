package com.rocy.tzclass21_bocast;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	private CloseScreenBroadcast closeScreenBroadcast;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_ON);
		closeScreenBroadcast = new CloseScreenBroadcast();
		registerReceiver(closeScreenBroadcast, filter);
		
	}
	
	public void click (View v){
		Intent intent = new Intent();
		intent.setAction("com.rocy.test1");
		
		this.sendOrderedBroadcast(intent, null);
	}
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		unregisterReceiver(closeScreenBroadcast);
		super.onDestroy();
	}
}
