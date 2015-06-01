package com.rocy.tzclass7;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	private SoundView soundView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		soundView=(SoundView)findViewById(R.id.soundView);
		
	}
	
	 @Override
	    public boolean onKeyUp(int keyCode, KeyEvent event) {
		 			if(keyCode==KeyEvent.KEYCODE_VOLUME_UP||keyCode==KeyEvent.KEYCODE_VOLUME_DOWN){
		 				//点击音量 用用子控件 onkeyup方法
		 				return soundView.onKeyUp(keyCode, event);
		 				
		 			}
		 			//否则用自己ide
		 			return super.onKeyUp(keyCode, event);
	    }
}
