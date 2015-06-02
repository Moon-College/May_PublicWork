package com.tz.ssk.customvoice;

import android.media.AudioManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private MyVoice mMyVoice;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mMyVoice = (MyVoice) findViewById(R.id.voice);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
			mMyVoice.mAudioManager.adjustStreamVolume(AudioManager.STREAM_RING,
					AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
		} else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
			mMyVoice.mAudioManager.adjustStreamVolume(AudioManager.STREAM_RING,
					AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
		} else {
			return super.onKeyDown(keyCode, event);
		}
		mMyVoice.currentvalue=mMyVoice.mAudioManager.getStreamVolume(AudioManager.STREAM_RING);
		mMyVoice.invalidate();
		return true;
	}
   
}
