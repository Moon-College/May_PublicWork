package cn.ysh.myservice;

import cn.ysh.myservice.MyService.MyBinder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

public class MainActivity extends Activity implements ServiceConnection, OnClickListener{
	private MyService myService;
	private MyBinder myBinder;
	private Button play, pause, continuePlay, stop;
	private Intent intent;  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		intent = new Intent();
		intent.setClass(this, MyService.class);
		startService(intent);
		bindService(intent, this, Context.BIND_AUTO_CREATE);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(myService != null){
			unbindService(this);
			stopService(intent);
		}
	}
	
	private void initView() {
		play = (Button) findViewById(R.id.playmusic);
		pause = (Button) findViewById(R.id.pausemusic);
		continuePlay = (Button) findViewById(R.id.continuemusic);
		stop = (Button) findViewById(R.id.stopmusic);
		play.setOnClickListener(this);
		pause.setOnClickListener(this);
		continuePlay.setOnClickListener(this);
		stop.setOnClickListener(this);
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		myBinder = (MyBinder) service;
		myService = myBinder.getMyService();
		Log.i("INFO", "service connected");
		
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		Log.i("INFO", "service disconnected");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.playmusic:
			try {
				myService.playMusic();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case R.id.pausemusic:
			myService.pauseMusic();
			break;
		case R.id.continuemusic:
			myService.continueMusic();
			break;
		case R.id.stopmusic:
			myService.stopMusic();
			break;

		default:
			break;
		}
		
	}
}
