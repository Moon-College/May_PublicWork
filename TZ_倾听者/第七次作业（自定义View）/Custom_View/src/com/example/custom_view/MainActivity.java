package com.example.custom_view;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;

import com.junwen.view.CustomView;

public class MainActivity extends Activity {
	//�Զ���ؼ�
	private CustomView view;
	//��Ƶ������
	private AudioManager am;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	/**
	 * ��ʼ��
	 */
	private void initView() {
		view = (CustomView) findViewById(R.id.view);
		am =(AudioManager) getSystemService(Context.AUDIO_SERVICE);
	}
	/**
	 * ��������
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_VOLUME_DOWN:
			//����������ʱ,ִ�н�������
			am.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
			//���������Զ���ؼ��ĵ�ǰ�������������»���
			view.setCurrVolume(am.getStreamVolume(AudioManager.STREAM_SYSTEM));
			//���»���
			view.invalidate();
			return true;
		case KeyEvent.KEYCODE_VOLUME_UP:
			//����������ʱ,ִ����������
			am.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
			view.setCurrVolume(am.getStreamVolume(AudioManager.STREAM_SYSTEM));
			view.invalidate();
			return true;
		default:
			return super.onKeyDown(keyCode, event);
		}
	}

}
