package com.itskylin.android.mp3service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

	private MediaPlayer mp;
	private String path = Environment.getExternalStorageDirectory()
			.getAbsolutePath();
	private String file = path + "/waiting.mp3";
	private int currentPosition;

	@Override
	public IBinder onBind(Intent intent) {
		MyBinder binder = new MyBinder();
		Log.i("INFO", "onBind");
		return binder;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Log.i("INFO", "onStart");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("INFO", "onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("INFO", "onDestroy");
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log.i("INFO", "onUnbind");
		return super.onUnbind(intent);
	}

	@Override
	public void onRebind(Intent intent) {
		super.onRebind(intent);
		Log.i("INFO", "onRebind");
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mp = new MediaPlayer();
	}

	public void play() {
		try {
			mp.reset();
			mp.setDataSource(file);
			mp.prepare();
			mp.setOnPreparedListener(new OnPreparedListener() {

				@Override
				public void onPrepared(MediaPlayer mp) {
					mp.start();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void pause() {
		if (mp.isPlaying()) {
			mp.pause();
			currentPosition = mp.getCurrentPosition();
		}
	}

	public void stop() {
		if (mp != null) {
			mp.stop();
		}
	}

	public void continueMusic() {
		if (currentPosition != -1) {
			mp.seekTo(currentPosition);
		} else {
			mp.reset();
		}
		mp.start();
	}

	public class MyBinder extends Binder {

		public MyService getMyService() {
			return MyService.this;
		}
	}

	public void check() {
		Log.i("INFO", "绑定成功！～～～～～～～");
	}
}
