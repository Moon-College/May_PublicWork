package com.zjm.custom;


import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;

import com.zjm.custom.view.SoundView;

public class MainActivity extends Activity {

//	AudioManager am;
	private SoundView sv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
//		am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		sv = (SoundView) findViewById(R.id.sv);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		// 音量降低
		case KeyEvent.KEYCODE_VOLUME_DOWN:
			//类型  调节方向
			sv.am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
			sv.setCurrentVolumn(sv.am.getStreamVolume(AudioManager.STREAM_MUSIC));
			sv.invalidate();//刷新
			
			return true;
		// 音量增大
		case KeyEvent.KEYCODE_VOLUME_UP:
			sv.am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
			sv.setCurrentVolumn(sv.am.getStreamVolume(AudioManager.STREAM_MUSIC));
			sv.invalidate();
			return true;
		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}
}
