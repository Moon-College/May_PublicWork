package com.decent.volumecontrol;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;

import com.decent.customview.DecentVolumeView;

public class DecentVolumeControlActivity extends Activity
{
	private DecentVolumeView mDVV;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mDVV = (DecentVolumeView) findViewById(R.id.dvv);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)
		{
			mDVV.getmAudioManager().adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
			mDVV.updateView();
			return true;
		}
		else if(keyCode == KeyEvent.KEYCODE_VOLUME_UP)
		{
			mDVV.getmAudioManager().adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
			mDVV.updateView();
			return true;	
		}
		else
		{
			// TODO Auto-generated method stub
			return super.onKeyDown(keyCode, event);			
		}
			
	}	
}