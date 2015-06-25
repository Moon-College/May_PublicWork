package com.knight.custom;

import com.knight.custom.view.SoundView;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	SoundView soundView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        soundView = (SoundView) findViewById(R.id.sound);
        
    }
    
    /**
     * 解决其他按键冲突
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	//拦截音量按键 
    	if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
    		//减小 按钮
    		soundView.audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, 
            		AudioManager.ADJUST_LOWER, 
            		AudioManager.FLAG_PLAY_SOUND);
    		//获取系统当前的媒体音量
        	soundView.setCurrentVolume(soundView.audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
        	soundView.invalidate();//重新调用onDraw方法重绘刷新
    		return true;
		} else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
			//增大 按钮
			soundView.audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, 
					AudioManager.ADJUST_RAISE, 
					AudioManager.FLAG_PLAY_SOUND);
			//获取系统当前的媒体音量
	    	soundView.setCurrentVolume(soundView.audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
	    	soundView.invalidate();//重新调用onDraw方法重绘刷新
			return true;
		}
    	
    	return super.onKeyDown(keyCode, event);
//    	return true;
    }
}
