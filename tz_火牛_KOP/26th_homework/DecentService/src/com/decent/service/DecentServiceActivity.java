package com.decent.service;

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
import android.widget.ImageButton;

import com.decent.decentutil.DecentLogUtil;
import com.decent.decentutil.ReflictionUtil;
import com.decent.service.service.DecentMusicService;
import com.decent.service.service.DecentMusicService.MusicServiceBinder;

public class DecentServiceActivity extends Activity implements OnClickListener,
		ServiceConnection {
	private ImageButton startBtn;
	private ImageButton pauseBtn;
	private ImageButton stopBtn;
	private Button exitBtn;
	private DecentMusicService musicService;
	private MusicServiceBinder musicServiceBinder;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ReflictionUtil.InjectionView(DecentServiceActivity.class.getName(),
				R.class.getName(), this);
		Intent service = new Intent();
		service.setClass(this, DecentMusicService.class);
		startService(service);
		bindService(service, this, Context.BIND_AUTO_CREATE);
		startBtn.setOnClickListener(this);
		pauseBtn.setOnClickListener(this);
		stopBtn.setOnClickListener(this);
		exitBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.startBtn:
			musicService.startPlayMusic();
			break;
		case R.id.pauseBtn:
			musicService.pausePalyMusic();
			break;
		case R.id.stopBtn:
			musicService.stopPlayMusic();
			break;
		case R.id.exitBtn:
			if (musicService != null) {
				musicService.stopPlayMusic();
				unbindService(this);
				musicService = null;
			}
			Intent service = new Intent();
			service.setClass(this, DecentMusicService.class);			
			stopService(service);
			finish();
			break;
		default:
			break;
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		DecentLogUtil.d("INFO", "into DecentServiceActivity onDestroy");
		/*
		 * 在 onDestroy里面如果不unbind的话,
		 * 会报android.app.ServiceConnectionLeaked
		 * 
		 * 其实就是想达到这样的效果：
		 * 1、播放器还在后台播放着，但是下次进入的时候需要重新bind，bind之后播放器还是之前的播放器，暂停的时间什么的都还记得
		 * 2、彻底的退出或者stopService另外搞一个按钮就可以，一般的音乐播放器也是这样做得 
		 */
		if (musicService != null) {
			unbindService(this);
			musicService = null;
		}
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		// TODO Auto-generated method stub
		DecentLogUtil.d("INFO", "into onServiceConnected");
		musicServiceBinder = (MusicServiceBinder) service;
		musicService = musicServiceBinder.getMusicService();
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		// TODO Auto-generated method stub
		DecentLogUtil.d("INFO", "into onServiceDisconnected");
		/*
		 * 这个是出现在service出了问题的时候才会被调用
		 * Called when a connection to the Service has been lost. 
		 * This typically happens when the process hosting the service has crashed or been killed.
		 */
	}

}