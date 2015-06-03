package com.tz.customcontrol;

import com.tz.customcontrol.view.SoundView;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;

public class MainActivity extends Activity {
	
	/**自定义音量控件*/
	private SoundView soundView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//初始化UI控件
		soundView = (SoundView) findViewById(R.id.soundView);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) { // 调低音量
			soundView.am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
		} else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) { // 调高音量
			soundView.am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
		}else {
			return super.onKeyDown(keyCode, event);  
		}
		// 获取系统当前的媒体音量
		soundView.setCurrentVolume(soundView.am.getStreamVolume(AudioManager.STREAM_MUSIC));
		// 重新调用onDraw方法进行重绘，刷新UI
		soundView.invalidate(); 
		return true;
	}

}
