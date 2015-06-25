package com.dd.thirtyhomework;

import android.media.AudioManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;

public class MainActivity extends Activity {
	SoundView soundView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		soundView = (SoundView) findViewById(R.id.soundview);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
			//设置ＦＸ，系统的也会显示
			soundView.am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FX_FOCUS_NAVIGATION_UP);
			soundView.setCurrentVolume(soundView.am.getStreamVolume(AudioManager.STREAM_MUSIC));
			soundView.invalidate();
			return true;
		}else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
			soundView.am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FX_FOCUS_NAVIGATION_UP);
			soundView.setCurrentVolume(soundView.am.getStreamVolume(AudioManager.STREAM_MUSIC));
			soundView.invalidate();
			return true;
		}else {
			return super.onKeyDown(keyCode, event);
		}
	}
}
