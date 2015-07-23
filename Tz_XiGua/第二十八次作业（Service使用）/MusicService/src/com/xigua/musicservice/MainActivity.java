package com.xigua.musicservice;

import java.io.IOException;

import com.xigua.musicservice.MusicService.MyBinder;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class MainActivity extends Activity implements OnClickListener, ServiceConnection {

	private Intent intent;
	private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8;
	private MusicService music_service;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
        intent = new Intent();
        intent.setClass(this, MusicService.class);
        this.startService(intent);
	    this.bindService(intent, this, Context.BIND_AUTO_CREATE);
	}
	
	private void initViews() {
		btn1 = (Button) findViewById(R.id.bt1);
		btn2 = (Button) findViewById(R.id.bt2);
		btn3 = (Button) findViewById(R.id.bt3);
		btn4 = (Button) findViewById(R.id.bt4);
		btn5 = (Button) findViewById(R.id.bt5);
		btn6 = (Button) findViewById(R.id.bt6);
		btn7 = (Button) findViewById(R.id.bt7);
		btn8 = (Button) findViewById(R.id.bt8);
//		btn1.setOnClickListener(this);
//      btn2.setOnClickListener(this);
//      btn3.setOnClickListener(this);
//      btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
        switch (v.getId()) {
		case R.id.bt1:
			this.startService(intent);
			break;
		case R.id.bt2:
			this.bindService(intent, this, Context.BIND_AUTO_CREATE);		
			break;
		case R.id.bt3:
			this.unbindService(this);
			break;
		case R.id.bt4:
			this.stopService(intent);
			break;
		case R.id.bt5:
			try {
				music_service.StartMusic();
			} catch (IllegalArgumentException | SecurityException
					| IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			break;
		case R.id.bt6:
			if(music_service!=null)
			music_service.PauseMusic();
			break;
		case R.id.bt7:
			if(music_service!=null)
			music_service.ContinueMusic();
			break;
		case R.id.bt8:
			if(music_service!=null)
			music_service.StopMusic();
			break;

		default:
			break;
		}		
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		Log.i("INFO", "service connected");
		MusicService.MyBinder binder = (MyBinder)service;
		music_service = binder.getService();
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		Log.i("INFO", "service disconnected");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(music_service!=null){
			this.unbindService(this);
		}
	}

	
}
