package com.ld.sounditem;

import com.ld.sounditem.view.soundView;

import android.media.AudioManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;

public class MainActivity extends Activity {

	private soundView soundview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		soundview = (soundView) findViewById(R.id.soundview);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_VOLUME_DOWN:
			soundview.am.adjustStreamVolume(AudioManager.STREAM_MUSIC,
					AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
			delectSound();
			return true;
		case KeyEvent.KEYCODE_VOLUME_UP:
			soundview.am.adjustStreamVolume(AudioManager.STREAM_MUSIC,
					AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
			delectSound();
			return true;
		default:
			return super.onKeyDown(keyCode, event);

		}
	}

	// 刷新
	public void delectSound() {
		soundview.setCurrentVolume(soundview.am
				.getStreamVolume(AudioManager.STREAM_MUSIC));// 获取系统当前音量
		soundview.invalidate();// 刷新重绘
	}
}
