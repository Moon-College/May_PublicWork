package com.dd.dd_service.service;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

/**
 * 1) 服务的启动方式  根据启动方式不同拥有不同的生命周期

-> startService()启动   oncreate  onstart   ->ondestroy
 
-> bindService()启动    oncreate  onbind   ->onunbind  ondestroy


1*服务如果一旦启动，就不会重新oncreate

2*服务只有在所有的绑定都解绑了或者所有启动的服务都停止了才会销毁


**如何保持服务长期存在，一般通过同时startservice  bindservice  退出程序时unbindservice


**如何保持服务长期运行
**广播接收者---->监听系统经常出现的广播，监听到了以后开启服务
 * @author dd
 *
 */
public class MyService extends Service {
	private MyBinder binder;
	private MediaPlayer mediaPlayer;
	@Override
	public IBinder onBind(Intent intent) {
		binder = new MyBinder();
		return binder;
	}
	
	@Override
	public void onCreate() {
		mediaPlayer = new MediaPlayer();
		super.onCreate();
	}

	public class MyBinder extends Binder{
		public MyService getMyService(){
			return MyService.this;
		}
	}

	public void playMusic() throws IllegalArgumentException, SecurityException, IllegalStateException, IOException {
		mediaPlayer.reset();//重置就是让播放器处于空闲状态
		mediaPlayer.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath()+"/歌曲名.mp3");
		mediaPlayer.prepare();//缓冲
		mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
			
			public void onPrepared(MediaPlayer mp) {
				// TODO Auto-generated method stub
				mp.start();
			}
		});
	}

	public void pauseMusic() {
		if(mediaPlayer.isPlaying()){
			mediaPlayer.pause();
		}
	}

	public void continuMusic() {
		mediaPlayer.start();
		
	}

	public void stopMusic() {
		mediaPlayer.stop();
	}
	public void check(){
		Log.i("INFO", "检查创建完成");
	}
}
