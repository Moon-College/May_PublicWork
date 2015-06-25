package com.jim.myaudio;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;

public class MyAudioActivity extends Activity {

	private MyAudioView audioView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		audioView = (MyAudioView) findViewById(R.id.audioView);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		switch (keyCode) {
		case KeyEvent.KEYCODE_VOLUME_UP:
			audioView.mAudioManager.adjustStreamVolume(
					AudioManager.STREAM_MUSIC,// ����,ý������
					AudioManager.ADJUST_RAISE,// ���򣬼�����Ч
					AudioManager.FLAG_PLAY_SOUND);// �в�����Ч
			audioView.setmCurrentSystemVome(audioView.mAudioManager
					.getStreamVolume(AudioManager.STREAM_MUSIC));
			audioView.invalidate();
			return true;
		case KeyEvent.KEYCODE_VOLUME_DOWN:
			audioView.mAudioManager.adjustStreamVolume(
					AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER,
					AudioManager.FLAG_PLAY_SOUND);
			audioView.setmCurrentSystemVome(audioView.mAudioManager
					.getStreamVolume(AudioManager.STREAM_MUSIC));
			audioView.invalidate();
			return true;
		default:
			return super.onKeyDown(keyCode, event);
		}

	}
}