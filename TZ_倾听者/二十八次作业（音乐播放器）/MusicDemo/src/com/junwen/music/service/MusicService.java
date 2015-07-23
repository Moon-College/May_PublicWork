package com.junwen.music.service;

import java.io.IOException;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Binder;
import android.os.IBinder;

public class MusicService extends Service implements OnPreparedListener{
	
	private MyBinder binder;
	private MediaPlayer player;
	@Override
	public IBinder onBind(Intent intent) {
		binder = new MyBinder();
		return binder;
	}
	@Override
	public void onCreate() {
		super.onCreate();
		player = new MediaPlayer();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	/**
	 * 内部类，返回Binder对象
	 * @author admi
	 *
	 */
	public class MyBinder extends Binder{
		public MusicService getService(){
			return MusicService.this;
		}
	}
	/**
	 * 播放音乐
	 * @param path
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public void playMusic(String path) throws IllegalArgumentException, SecurityException, IllegalStateException, IOException{
		player.reset();
		player.setDataSource(path);
		player.prepare();
		player.setOnPreparedListener(this);
	}
	/**
	 * 暂停音乐
	 */
	public void pauseMusic(){
		if(player.isPlaying()){
			player.pause();
		}
	}
	/**
	 * 继续播放
	 */
	public void continuMusic(){
		if(!player.isPlaying()){
			player.start();
		}
	}
	/**
	 * 绑定完成后
	 */
	@Override
	public void onPrepared(MediaPlayer mp) {
		player.start();
	}
}
