package com.org.view.main;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.example.home_test_myview.R;
import com.org.view.myview.MyView;

public class MainActivity extends Activity {
    MyView myView;
    private float startX;
    private float startY;
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		myView = (MyView) findViewById(R.id.myview);
		myView.setOnTouchListener(new MyOnTouchListener());
	}
	
	private class MyOnTouchListener implements OnTouchListener{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
           //���Ȼ�ȡ����ʱ������
		  switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startX = event.getX();
			startY = event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			
			break;
		case MotionEvent.ACTION_UP:
			
			break;
		default:
			break;
		}
			return false;
		}
		
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
      if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
    	  myView.am.adjustStreamVolume(AudioManager.STREAM_MUSIC,
    			  AudioManager.ADJUST_LOWER, // ����
    			  AudioManager.FLAG_PLAY_SOUND);
      }else if(keyCode == KeyEvent.KEYCODE_VOLUME_UP){
    	    myView.am.adjustStreamVolume(AudioManager.STREAM_MUSIC, 
    	    		AudioManager.ADJUST_RAISE,
    	    		AudioManager.FLAG_PLAY_SOUND);
      }
      //��ȡ��ǰϵͳ������
       myView.setCurrentVolume(myView.am.getStreamVolume(AudioManager.STREAM_MUSIC));
       myView.invalidate(); //���µ��� onDraw ��ˢ��u
      
      
		return true;
	}
}
