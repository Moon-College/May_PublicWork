package com.rocy.tzClass22;

import com.rocy.tzClass13.R;
import com.rocy.tzClass22.MyService.MyBind;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity  {

	private boolean bindService;
	private MyServiceConnection myServiceConnection;
	private Intent muiscIntent;
	private MyService myService;
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		muiscIntent = new Intent();
		muiscIntent.setAction("com.rocy.tz");
		this.startService(muiscIntent);
		myServiceConnection = new MyServiceConnection();
		bindService = this.bindService(muiscIntent,myServiceConnection , Context.BIND_AUTO_CREATE);
	}
	
	
	//播放
	public void play(View v){
		TextView text =(TextView) v;
		switch (myService.flag) {
		case MyService.CREATE:
			myService.start();
			text.setText("暂停");
			break;
		case MyService.START:
			myService.playerStart();
			text.setText("暂停");
			break;
		case MyService.STOP:
			myService.playerPause();
			text.setText("播放");
			break;
		case MyService.ERROR:
			Toast.makeText(this, "读取文件失败over", 0).show();
			this.finish();

		default:
			break;
		}
	}
	
	
	//退出
	public void exists(View v){
		this.stopService(muiscIntent);
		this.finish();
	}
	
	
    public class MyServiceConnection implements ServiceConnection{

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			MyBind bind = (MyService.MyBind)service;
			myService = bind.getService();
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    
    @Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
    	super.onDestroy();
    	if(bindService){
    		this.unbindService(myServiceConnection);
    	}
    }
	
}
