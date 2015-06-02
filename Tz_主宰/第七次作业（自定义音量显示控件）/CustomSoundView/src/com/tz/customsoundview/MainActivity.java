package com.tz.customsoundview;

import com.tz.customsoundview.view.CustomSoundView;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;

public class MainActivity extends Activity {

	private CustomSoundView customSoundView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		customSoundView = (CustomSoundView) findViewById(R.id.custom_roundview);
		customSoundView.invalidate();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		AudioManager am = customSoundView.getAudioManager();
		switch (keyCode) {
		case KeyEvent.KEYCODE_VOLUME_DOWN:
			am.adjustStreamVolume(AudioManager.STREAM_MUSIC,
					AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
			customSoundView.invalidate();
			
			return true;
		case KeyEvent.KEYCODE_VOLUME_UP:
			am.adjustStreamVolume(AudioManager.STREAM_MUSIC,
					AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);

			customSoundView.invalidate();
			
			return true;

		default:
			return super.onKeyDown(keyCode, event);
		}
	}
}
