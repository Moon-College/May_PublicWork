package com.itskylin.android.customvolume;

import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.itskylin.android.customvolume.view.CustomeVolume;
import com.itskylin.framework.ioc.annotation.ContentView;
import com.itskylin.framework.ioc.annotation.ViewInject;
import com.itskylin.framework.ioc.view.FrameworkActivity;

@ContentView(R.layout.activity_main)
public class MainActivity extends FrameworkActivity implements OnTouchListener {

	@ViewInject(R.id.customeVolume)
	private CustomeVolume cv;
	@ViewInject(R.id.customeVolume2)
	private CustomeVolume cv2;
	@ViewInject(R.id.customeVolume3)
	private CustomeVolume cv3;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_VOLUME_DOWN:
			cv.setVolumeDown();
			cv2.invalidate();
			cv3.invalidate();
			return true;
		case KeyEvent.KEYCODE_VOLUME_UP:
			cv.setVolumeUp();
			cv2.invalidate();
			cv3.invalidate();
			return true;
		default:
			return super.onKeyDown(keyCode, event);
		}

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		cv.onTouchEvent(event);
		cv2.onTouchEvent(event);
		cv3.onTouchEvent(event);
		return false;
	}
}
