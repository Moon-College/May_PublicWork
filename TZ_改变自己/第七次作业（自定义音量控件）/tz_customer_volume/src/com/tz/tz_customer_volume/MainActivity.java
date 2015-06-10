package com.tz.tz_customer_volume;

import com.tz.tz_customer_volume.view.SoundView;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;

public class MainActivity extends Activity {

	private SoundView soundView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		soundView = (SoundView) findViewById(R.id.soundView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
			soundView.am.adjustStreamVolume(AudioManager.STREAM_MUSIC, 
					AudioManager.ADJUST_LOWER, 
					AudioManager.FLAG_PLAY_SOUND);
		}else if(keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
			soundView.am.adjustStreamVolume(AudioManager.STREAM_MUSIC, 
					AudioManager.ADJUST_RAISE, 
					AudioManager.FLAG_PLAY_SOUND);
		}
		//获取当前媒体的音量
		soundView.setCurrentVolume(soundView.am.getStreamVolume(AudioManager.STREAM_MUSIC));
		soundView.invalidate();
		return true;
	}

}
