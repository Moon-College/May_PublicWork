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
			// �ֶ���������
			soundView.am.adjustStreamVolume(AudioManager.STREAM_MUSIC,
					AudioManager.ADJUST_LOWER, // ���߻��ǵ���
					AudioManager.FLAG_PLAY_SOUND);// ����������ʱ�� ��һЩģʽ�������𶯣���ʾ��������
			// ��ȡϵͳ��ǰ������
			soundView.setCurrentVolume(soundView.am
					.getStreamVolume(AudioManager.STREAM_MUSIC));
			soundView.invalidate();// ���µ���ondraw���������ػ�
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
			soundView.am.adjustStreamVolume(AudioManager.STREAM_MUSIC,
					AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
			// ��ȡϵͳ��ǰ������
			soundView.setCurrentVolume(soundView.am
					.getStreamVolume(AudioManager.STREAM_MUSIC));
			soundView.invalidate();// ���µ���ondraw���������ػ�
			return true;
		}

		return super.onKeyDown(keyCode, event);

		// // ��ȡϵͳ��ǰ������
		// sond.setCurrentVolume(sond.am
		// .getStreamVolume(AudioManager.STREAM_MUSIC));
		// sond.invalidate();//���µ���ondraw���������ػ�
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
