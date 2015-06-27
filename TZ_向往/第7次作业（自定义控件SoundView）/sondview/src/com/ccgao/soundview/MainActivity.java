package com.ccgao.soundview;

import com.ccgao.soundview.myview.MySoundView;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;

public class MainActivity extends Activity {
	private MySoundView mysv;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mysv = (MySoundView) findViewById(R.id.mysv);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		boolean isTrue = false;
		if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
			mysv.am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
			isTrue = true;
		} else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
			mysv.am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
			isTrue = true;
		}
		if (isTrue) {
			mysv.setCurrenVolume(mysv.am.getStreamVolume(AudioManager.STREAM_MUSIC));
			mysv.invalidate();
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}
}