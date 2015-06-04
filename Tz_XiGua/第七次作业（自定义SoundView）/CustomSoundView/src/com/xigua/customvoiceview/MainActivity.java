package com.xigua.customvoiceview;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity {

	SoundView soundView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		soundView = (SoundView) findViewById(R.id.soundview);
		
	}

	 @Override
	    public boolean onKeyDown(int keyCode, KeyEvent event) {
	    	if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
	    		soundView.am.adjustStreamVolume(AudioManager.STREAM_MUSIC,
	    				AudioManager.ADJUST_LOWER, 
	    				AudioManager.FLAG_PLAY_SOUND);
		    	//获取系统当前媒体音量
		    	soundView.setCurrentVolume(soundView.am.getStreamVolume(AudioManager.STREAM_MUSIC));
		    	soundView.invalidate();//重新调用ondraw方法进行重绘
		    	return true;
	    	} else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
	    		soundView.am.adjustStreamVolume(AudioManager.STREAM_MUSIC,
	    				AudioManager.ADJUST_RAISE, 
	    				AudioManager.FLAG_PLAY_SOUND);
		    	//获取系统当前媒体音量
		    	soundView.setCurrentVolume(soundView.am.getStreamVolume(AudioManager.STREAM_MUSIC));
		    	soundView.invalidate();//重新调用ondraw方法进行重绘
		    	return true;
	    	}
	    	else 
	    		return super.onKeyDown(keyCode, event);

	    }

}
