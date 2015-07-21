package com.xigua.broadcast;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity {

	private KillBackgroundTaskReceiver kbr;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//        Intent broadcast = new Intent();
//        broadcast.setAction("com.xigua.intercept");
//        sendOrderedBroadcast(broadcast, null);
		
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		kbr = new KillBackgroundTaskReceiver();
		registerReceiver(kbr, filter);
	}

	@Override
	protected void onDestroy() { 
		super.onDestroy();
		this.unregisterReceiver(kbr);
	}

}
