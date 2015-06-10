package com.qfx.soundview;

import com.qfx.soundview.view.SoundView;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;

public class MainActivity extends Activity {
	
	private SoundView sv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		sv = (SoundView) findViewById(R.id.soundView);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
			sv.adjustDownVolume();
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
			sv.adjustUpVolume();
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
		
	}
}
