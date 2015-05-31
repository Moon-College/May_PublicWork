package com.itskylin.android.customvolume;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

import com.itskylin.android.customvolume.view.CustomeVolume;

public class MainActivity extends Activity implements OnTouchListener {

	private CustomeVolume cv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		cv = (CustomeVolume) findViewById(R.id.customeVolume);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_VOLUME_DOWN:
			cv.setVolumeDown();
			return true;
		case KeyEvent.KEYCODE_VOLUME_UP:
			cv.setVolumeUp();
			return true;
		default:
			return super.onKeyDown(keyCode, event);
		}

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		cv.onTouchEvent(event);
		return false;
	}
}
