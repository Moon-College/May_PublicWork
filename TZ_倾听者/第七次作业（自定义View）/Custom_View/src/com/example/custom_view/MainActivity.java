package com.example.custom_view;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;

import com.junwen.view.CustomView;

public class MainActivity extends Activity {
	//自定义控件
	private CustomView view;
	//音频管理器
	private AudioManager am;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	/**
	 * 初始化
	 */
	private void initView() {
		view = (CustomView) findViewById(R.id.view);
		am =(AudioManager) getSystemService(Context.AUDIO_SERVICE);
	}
	/**
	 * 监听按键
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_VOLUME_DOWN:
			//降低音量键时,执行降低音量
			am.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
			//重新设置自定义控件的当前音量，让他重新绘制
			view.setCurrVolume(am.getStreamVolume(AudioManager.STREAM_SYSTEM));
			//重新绘制
			view.invalidate();
			return true;
		case KeyEvent.KEYCODE_VOLUME_UP:
			//上升音量键时,执行上升音量
			am.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
			view.setCurrVolume(am.getStreamVolume(AudioManager.STREAM_SYSTEM));
			view.invalidate();
			return true;
		default:
			return super.onKeyDown(keyCode, event);
		}
	}

}
