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
  
    
   //调节音量
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	// TODO Auto-generated method stub
    	
    	//得到当前的音量currentVolum
    	/**
    	 * return super.onKeyDown(keyCode, event);是系统调整音量并返回；
    	 * 而我们需要的是系统调整音量后的当前音量，故要手动改调节音量而不用该return。。。
    	 */
    	//降低音量
    	
    	if(keyCode==KeyEvent.KEYCODE_VOLUME_DOWN){
    		soundView.am.adjustStreamVolume(AudioManager.STREAM_MUSIC,
    				AudioManager.ADJUST_LOWER,
    				AudioManager.FLAG_PLAY_SOUND);
    	}else if(keyCode == KeyEvent.KEYCODE_VOLUME_UP){
    		soundView.am.adjustStreamVolume(AudioManager.STREAM_MUSIC,
    				AudioManager.ADJUST_RAISE,
    				AudioManager.FLAG_PLAY_SOUND);
    	}
    	//获取当前音量值
    	soundView.setCurrentVolume(soundView.am.getStreamVolume(AudioManager.STREAM_MUSIC));
    	soundView.invalidate();//重新调用ondraw方法进行重绘
    	
    	return true;
    }
        
}
