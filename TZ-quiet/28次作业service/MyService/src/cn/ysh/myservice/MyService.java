package cn.ysh.myservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service{
	private MediaPlayer mediaPlayer;
	
	@Override
	public void onCreate() {
		super.onCreate();
		mediaPlayer = new MediaPlayer();
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		Log.i("INFO", "onBind");
		return new MyBinder();
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		Log.i("INFO", "onUnbind");
		return super.onUnbind(intent);
	}
	
	public void playMusic() throws IllegalArgumentException, SecurityException, IllegalStateException, IOException{
		mediaPlayer.reset();
//		File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/mo.mp3");
//		FileInputStream fis = new FileInputStream(file);
//		mediaPlayer.setDataSource(fis.getFD());
		mediaPlayer.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath()+"/mo.mp3");
		mediaPlayer.prepare();
		mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
			
			@Override
			public void onPrepared(MediaPlayer mp) {
				mp.start();
				
			}
		});
	}
	
	public void pauseMusic(){
		if(mediaPlayer.isPlaying()){
			mediaPlayer.pause();
		}
	}
	
	public void continueMusic(){
		mediaPlayer.start();
	}
	
	public void stopMusic(){
		mediaPlayer.stop();
	}
	
	public class MyBinder extends Binder{
		public MyService getMyService(){
			return MyService.this;
		}
	}

}
