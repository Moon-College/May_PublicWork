package com.chris.soundviewdemo;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;

public class SoundViewDemoActivity extends Activity
{
	SoundView mSoundView;
	int streamType = AudioManager.STREAM_MUSIC;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sound_view_demo);
		mSoundView = (SoundView) findViewById(R.id.sv);
		mSoundView.setmStreamType(streamType);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		int type = mSoundView.getmStreamType();
		int mCurVolume = 0;
		boolean ret;
		//处理音量加减
		switch (keyCode)
		{
		case KeyEvent.KEYCODE_VOLUME_DOWN:
			mSoundView.getmAudioManager().adjustStreamVolume(type, AudioManager.ADJUST_LOWER,
					AudioManager.FLAG_PLAY_SOUND);
			ret = true;
			break;
		case KeyEvent.KEYCODE_VOLUME_UP:
			mSoundView.getmAudioManager().adjustStreamVolume(type, AudioManager.ADJUST_RAISE,
					AudioManager.FLAG_PLAY_SOUND);
			ret = true;
			break;
		default:
			//放行其他按键
			ret = super.onKeyDown(keyCode, event);
			break;
		}
		mCurVolume = mSoundView.getmAudioManager().getStreamVolume(type);
		mSoundView.setmCurVolume(mCurVolume);
		mSoundView.invalidate();
		
		return ret;
	}
}
