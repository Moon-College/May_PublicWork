package com.decent.service.service;

import com.decent.decentutil.DecentLogUtil;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;

public class DecentMusicService extends Service implements OnPreparedListener {

	private static final String TAG = "DecentMusicService";
	private MediaPlayer mMediaPlayer;
	private String musicPath = Environment.getExternalStorageDirectory()
			.getAbsolutePath() + "/riverside.mp3";
	private MusicServiceBinder musicServiceBinder;
	private int pausePosition = -1;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		DecentLogUtil.d(TAG, "into onCreate");
		mMediaPlayer = new MediaPlayer();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		DecentLogUtil.d(TAG, "into onBind");
		musicServiceBinder = new MusicServiceBinder();
		return musicServiceBinder;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		DecentLogUtil.d(TAG, "into onStart");
		super.onStart(intent, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		DecentLogUtil.d(TAG, "into onDestroy");
		super.onDestroy();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		DecentLogUtil.d(TAG, "into onUnbind");
		return super.onUnbind(intent);
	}

	public class MusicServiceBinder extends Binder {
		public DecentMusicService getMusicService() {
			return DecentMusicService.this;
		}
	}

	public void startPlayMusic() {
		if (mMediaPlayer.isPlaying()) {
			return;
		}
		try {
			mMediaPlayer.reset();
			mMediaPlayer.setDataSource(musicPath);
			mMediaPlayer.prepare();
			mMediaPlayer.setOnPreparedListener(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void pausePalyMusic() {
		if (mMediaPlayer.isPlaying()) {
			mMediaPlayer.pause();
			pausePosition = mMediaPlayer.getCurrentPosition();
		}
	}

	public void stopPlayMusic() {
		mMediaPlayer.stop();
		mMediaPlayer.getCurrentPosition();
		mMediaPlayer.getDuration();
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		// TODO Auto-generated method stub
		if(pausePosition!=-1){
			mp.seekTo(pausePosition);
			pausePosition = -1;
		}
		mp.start();
	}
}
