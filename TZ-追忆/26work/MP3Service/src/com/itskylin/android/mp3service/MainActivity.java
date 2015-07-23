package com.itskylin.android.mp3service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.itskylin.android.mp3service.MyService.MyBinder;

public class MainActivity extends Activity implements OnClickListener,
		ServiceConnection {

	private Button play, pause, stop, jixu;
	private MyService myService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		play = (Button) this.findViewById(R.id.play);
		pause = (Button) this.findViewById(R.id.pause);
		stop = (Button) this.findViewById(R.id.stop);
		jixu = (Button) this.findViewById(R.id.jixu);
		play.setOnClickListener(this);
		pause.setOnClickListener(this);
		stop.setOnClickListener(this);
		jixu.setOnClickListener(this);
		startBindService();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.play:
			myService.play();
			break;
		case R.id.pause:
			myService.pause();
			break;
		case R.id.jixu:
			Toast.makeText(this, "onclick", 1000).show();
			myService.continueMusic();
			break;
		case R.id.stop:
			myService.stop();
			this.unbindService(this);
			break;

		default:
			break;
		}
	}

	private void startBindService() {
		Intent intent = new Intent(this, MyService.class);
		this.startService(intent);
		this.bindService(intent, this, Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (myService != null) {
			this.unbindService(this);
		}
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		MyService.MyBinder binder = (MyBinder) service;
		myService = binder.getMyService();
		myService.check();
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		this.unbindService(this);
	}
}