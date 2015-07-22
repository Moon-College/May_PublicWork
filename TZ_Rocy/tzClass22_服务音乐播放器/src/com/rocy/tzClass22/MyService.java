package com.rocy.tzClass22;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;

public class MyService extends Service {
	public static final int CREATE = 1;
	public static final int START = 2;
	public static final int STOP = 3;
	public static final int ERROR = 0;
	private MyBind bind;
	private MediaPlayer player ;
	public int flag;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		player = new MediaPlayer();
		player.reset();
		flag = CREATE;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		bind = new MyBind();
		return bind;
	}
	
	public void start(){
		if(player !=null ){
			try {
				player.setDataSource(Environment.getExternalStorageDirectory()+"/youhappy.mp3");
			    player.prepare();
			  
			    player.setOnPreparedListener(new OnPreparedListener() {
					
					@Override
					public void onPrepared(MediaPlayer mp) {
						// TODO Auto-generated method stub
						mp.start();
						flag = STOP;
					}
				});
			    
			    player.setOnCompletionListener(new OnCompletionListener() {
					
					@Override
					public void onCompletion(MediaPlayer mp) {
						// TODO Auto-generated method stub
						mp.start();
					}
				});
			} catch (Exception e) {
				flag = ERROR;
				// TODO Auto-generated catch block
			}
		}
		
	}
	
	public void playerPause(){
		if(player != null && player.isPlaying()){
			player.pause();
			flag = START;
		}
	}
	
	public void playerStart(){
		if(player != null && !player.isPlaying()){
			player.start();
			flag = STOP;
		}
	}
	
	
	
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(player != null){
			player.stop();
			player.release();
			flag = CREATE;
		}
	}
	
	
	
	public class MyBind extends Binder{
		
		
		 //获得服务
		 public MyService getService(){
			return MyService.this;
		 }
		
		
	}
	

}
