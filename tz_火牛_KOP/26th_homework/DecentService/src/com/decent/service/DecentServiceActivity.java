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
		 * �� onDestroy���������unbind�Ļ�,
		 * �ᱨandroid.app.ServiceConnectionLeaked
		 * 
		 * ��ʵ������ﵽ������Ч����
		 * 1�����������ں�̨�����ţ������´ν����ʱ����Ҫ����bind��bind֮�󲥷�������֮ǰ�Ĳ���������ͣ��ʱ��ʲô�Ķ����ǵ�
		 * 2�����׵��˳�����stopService�����һ����ť�Ϳ��ԣ�һ������ֲ�����Ҳ���������� 
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
		 * ����ǳ�����service���������ʱ��Żᱻ����
		 * Called when a connection to the Service has been lost. 
		 * This typically happens when the process hosting the service has crashed or been killed.
		 */
	}

}