package com.zht.service;

import java.io.IOException;
import java.io.InputStream;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;

public class MusicService extends Service {

	private MediaPlayer mp;

	@Override
	public void onCreate() {
		super.onCreate();
		mp = new MediaPlayer();

	}

	@Override
	public IBinder onBind(Intent intent) {
		return new MyBinder();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

	}

	protected void doPause() {
		mp.pause();
	}

	protected void doResume() {
		if(mp == null){
			mp = new MediaPlayer();
		}
		mp.start();
	}

	protected void doStop() {
		if (mp != null && mp.isPlaying()) {
			mp.stop();
		}
		mp.release();
		mp = null;
	}

	protected void doStart() {
		if (mp == null) {
			mp = new MediaPlayer();
		}
		try {
			mp.reset();
			//拿asset里的音乐文件
			AssetFileDescriptor afd = getAssets().openFd("music/abc.mp3");
			System.out.println(afd.getFileDescriptor());
			mp.setDataSource(afd.getFileDescriptor());
			mp.prepare();
			mp.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class MyBinder extends Binder {
		public MusicService getMusicService() {
			return MusicService.this;
		}
	}

}
