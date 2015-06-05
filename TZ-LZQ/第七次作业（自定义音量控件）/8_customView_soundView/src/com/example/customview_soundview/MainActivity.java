package com.example.customview_soundview;

import com.example.customview_soundview.view.SoundView;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

public class MainActivity extends Activity {

	private SoundView soundView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		soundView = (SoundView) findViewById(R.id.sond);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
			// 手动调节音量
			soundView.am.adjustStreamVolume(AudioManager.STREAM_MUSIC,
					AudioManager.ADJUST_LOWER, // 调高还是调低
					AudioManager.FLAG_PLAY_SOUND);// 调节音量的时候 的一些模式，比如震动，显示音量画面
			// 获取系统当前的音量
			soundView.setCurrentVolume(soundView.am
					.getStreamVolume(AudioManager.STREAM_MUSIC));
			soundView.invalidate();// 重新调用ondraw方法进行重绘
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
			soundView.am.adjustStreamVolume(AudioManager.STREAM_MUSIC,
					AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
			// 获取系统当前的音量
			soundView.setCurrentVolume(soundView.am
					.getStreamVolume(AudioManager.STREAM_MUSIC));
			soundView.invalidate();// 重新调用ondraw方法进行重绘
			return true;
		}

		return super.onKeyDown(keyCode, event);

		// // 获取系统当前的音量
		// sond.setCurrentVolume(sond.am
		// .getStreamVolume(AudioManager.STREAM_MUSIC));
		// sond.invalidate();//重新调用ondraw方法进行重绘
		// // sond.am.getStreamVolume(streamType)
		// // return super.onKeyDown(keyCode, event);
		// return true;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
//		return super.onTouchEvent(event);
		soundView.onTouchEvent(event);
		return false;
	}
}
