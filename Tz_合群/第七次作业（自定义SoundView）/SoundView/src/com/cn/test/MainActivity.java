package com.cn.test;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;

/**
 * Created on2015-5-30 上午9:22:29 MainActivity.java<br/>
 * Author ZhuHequn 1363790125@qq.com <br/>
 * version 1.0 TODO
 */
public class MainActivity extends Activity {
	private SoundViewActivity control;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();

	}

	private void initView() {
		// TODO Auto-generated method stub
		control = (SoundViewActivity) findViewById(R.id.control);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		//截取两个按钮事件
		switch (keyCode) {
		case KeyEvent.KEYCODE_VOLUME_DOWN:
			control.mAudioManager.adjustStreamVolume(
					AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER,
					AudioManager.FLAG_PLAY_SOUND);
			doInvalidate();
			return true;
		case KeyEvent.KEYCODE_VOLUME_UP:
			control.mAudioManager.adjustStreamVolume(
					AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE,
					AudioManager.FLAG_PLAY_SOUND);
			doInvalidate();
			return true;
		default:
			//其他事件顺利通过
			return super.onKeyDown(keyCode, event);
		}
		
	}

	private void doInvalidate() {
		control.setmCurrentVolume(control.mAudioManager
				.getStreamVolume(AudioManager.STREAM_MUSIC));
		control.invalidate();		
	}
}
