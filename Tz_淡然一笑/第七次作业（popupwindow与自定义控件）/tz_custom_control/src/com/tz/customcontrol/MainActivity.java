package com.tz.customcontrol;

import com.tz.customcontrol.view.SoundView;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;

public class MainActivity extends Activity {
	
	/**�Զ��������ؼ�*/
	private SoundView soundView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//��ʼ��UI�ؼ�
		soundView = (SoundView) findViewById(R.id.soundView);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) { // ��������
			soundView.am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
		} else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) { // ��������
			soundView.am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
		}else {
			return super.onKeyDown(keyCode, event);  
		}
		// ��ȡϵͳ��ǰ��ý������
		soundView.setCurrentVolume(soundView.am.getStreamVolume(AudioManager.STREAM_MUSIC));
		// ���µ���onDraw���������ػ棬ˢ��UI
		soundView.invalidate(); 
		return true;
	}

}
