package com.tz.michael.activity;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;

public class MyServiceTest extends Service {

	private MediaPlayer mediaPlayer;

	@Override
	public IBinder onBind(Intent intent) {
		MyBinder myBinder=new MyBinder();
		return myBinder;
	}

	/**
	 * 只会存在一个实例
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		mediaPlayer = new MediaPlayer();
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
	}
	
	public class MyBinder extends Binder{
		/**
		 * 将外部类对象返回去
		 * @return
		 */
		public MyServiceTest getService(){
			return MyServiceTest.this;
		}
	}
	
	/**
	 * 播放的方法
	 */
	public void playMusic(){
		mediaPlayer.reset();//重置就是让播放器处于空闲状态
		try {
			mediaPlayer.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Gogomusic.mp3");
			mediaPlayer.prepare();//缓冲
			mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
				
				public void onPrepared(MediaPlayer mp) {
					mp.start();
				}
			});
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 暂停音乐
	 */
	public void pauseMusic(){
		if(mediaPlayer.isPlaying()){
			mediaPlayer.pause();
		}
	}
	/**
	 * 继续播放，这里因为是同一首，且没有进度条，故直接启动即可
	 */
	public void continueMusic(){
		mediaPlayer.start();
	}
	/**
	 * 停止播放
	 */
	public void stopMusic(){
		mediaPlayer.stop();
	}
}
