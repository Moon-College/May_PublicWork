package com.myandroid.coustom_volume;

import com.myandroid.coustom_volume.view.SoundView;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	private SoundView soundView;
	private boolean back;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		soundView = (SoundView) findViewById(R.id.soundView);

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		switch (keyCode) {
		case KeyEvent.KEYCODE_VOLUME_DOWN:
			soundView.am.adjustStreamVolume(AudioManager.STREAM_MUSIC,
					AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
			back = true;
			break;
		case KeyEvent.KEYCODE_VOLUME_UP:
			soundView.am.adjustStreamVolume(AudioManager.STREAM_MUSIC,
					AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
			back = true;
			break;
		default:
			back=super.onKeyDown(keyCode, event);
			break;
		}
		soundView.setCurrentVolume(soundView.am.getStreamVolume(AudioManager.STREAM_MUSIC));
		soundView.invalidate();
		return back;
	}
}