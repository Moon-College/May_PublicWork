package cn.ysh.mybroadcastreceiver;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;

public class MainActivity extends Activity {
	private KillBackgroundProcess kill;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		kill = new KillBackgroundProcess(this);
	}
	
	public void send(View v){
		Intent intent = new Intent();
		intent.setAction("cn.ysh.zz");
		this.sendOrderedBroadcast(intent, null);
	}
	
	public void killbackgroundprocess(View v){
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
		this.registerReceiver(kill, intentFilter);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		this.unregisterReceiver(kill);
	}

}
