package com.p2y9y.soundview;

import com.p2y9y.soundview.view.SoundView;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;

public class MainActivity extends Activity {
	
	SoundView mSoundView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		mSoundView = (SoundView) findViewById(R.id.soundView);
	}
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN || keyCode == KeyEvent.KEYCODE_VOLUME_UP){
			if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
				mSoundView.mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, 
						AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
			}else if(keyCode == KeyEvent.KEYCODE_VOLUME_UP){
				mSoundView.mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, 
						AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
			}
			mSoundView.setCurrentVolume();
			mSoundView.invalidate();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}
}
