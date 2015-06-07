package com.xr.sound_view;

import com.xr.sound_view.customerview.SoundView;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {
   
    SoundView soundView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        soundView=(SoundView) findViewById(R.id.soundView);
    }
  
    
   //��������
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	// TODO Auto-generated method stub
    	
    	//�õ���ǰ������currentVolum
    	/**
    	 * return super.onKeyDown(keyCode, event);��ϵͳ�������������أ�
    	 * ��������Ҫ����ϵͳ����������ĵ�ǰ��������Ҫ�ֶ��ĵ������������ø�return������
    	 */
    	//��������
    	
    	if(keyCode==KeyEvent.KEYCODE_VOLUME_DOWN){
    		soundView.am.adjustStreamVolume(AudioManager.STREAM_MUSIC,
    				AudioManager.ADJUST_LOWER,
    				AudioManager.FLAG_PLAY_SOUND);
    	}else if(keyCode == KeyEvent.KEYCODE_VOLUME_UP){
    		soundView.am.adjustStreamVolume(AudioManager.STREAM_MUSIC,
    				AudioManager.ADJUST_RAISE,
    				AudioManager.FLAG_PLAY_SOUND);
    	}
    	//��ȡ��ǰ����ֵ
    	soundView.setCurrentVolume(soundView.am.getStreamVolume(AudioManager.STREAM_MUSIC));
    	soundView.invalidate();//���µ���ondraw���������ػ�
    	
    	return true;
    }
        
}
