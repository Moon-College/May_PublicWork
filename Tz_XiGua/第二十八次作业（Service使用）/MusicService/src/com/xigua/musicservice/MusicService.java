package com.xigua.musicservice;

import java.io.File;
import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

public class MusicService extends Service {

	MediaPlayer mp;
	@Override
	public IBinder onBind(Intent intent) {
		Log.i("INFO", "service bind");
		return new MyBinder();
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mp = new MediaPlayer();
		Log.i("INFO", "service create");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("INFO", "service start");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("INFO", "service destroy");
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log.i("INFO", "service unbind");
		return super.onUnbind(intent);
	}

	class MyBinder extends Binder{
		public MusicService getService(){
			return MusicService.this;
		}
	}
	
	public void StartMusic() throws IllegalArgumentException, SecurityException, IllegalStateException, IOException{
		mp.reset();
		mp.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"music"+"/xijuzhiwang.mp3");
	    mp.prepare();
	    mp.setOnPreparedListener(new OnPreparedListener() {
			
			@Override
			public void onPrepared(MediaPlayer mp) {
				mp.start();
			}
		});
	}
	
	public void PauseMusic(){
		if(mp.isPlaying()){
			mp.pause();
		}
	}
	
	public void ContinueMusic(){
		mp.start();
	}
	
	public void StopMusic(){
		mp.stop();
	}
         
}
