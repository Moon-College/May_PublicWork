package com.caist.hc;

import com.caist.selfcontrol.SoundView;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;

public class MyselfControlActivity extends Activity {
    /** Called when the activity is first created. */
    SoundView soundView;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	// TODO Auto-generated method stub
      soundView = (SoundView) findViewById(R.id.sv);
      if(keyCode==KeyEvent.KEYCODE_VOLUME_DOWN||keyCode==KeyEvent.KEYCODE_VOLUME_UP){
       switch(keyCode){
       case KeyEvent.KEYCODE_VOLUME_DOWN:
   		soundView.am.adjustStreamVolume(AudioManager.STREAM_MUSIC, 
				AudioManager.ADJUST_LOWER,
				AudioManager.FLAG_PLAY_SOUND);  
       break;
       case KeyEvent.KEYCODE_VOLUME_UP:
   		soundView.am.adjustStreamVolume(AudioManager.STREAM_MUSIC, 
				AudioManager.ADJUST_RAISE,
				AudioManager.FLAG_PLAY_SOUND);   		
   		break;
   		default:
   			break;
       }
       soundView.setCurrentVolume(soundView.am.getStreamVolume(AudioManager.STREAM_MUSIC));
       soundView.invalidate();     
       return true;
      }
	  return super.onKeyDown(keyCode, event);
    }
}