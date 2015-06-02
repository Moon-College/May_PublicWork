package com.limz.mycustomviewdemo.activity;

import com.limz.mycustomviewdemo.view.MySoundView;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

public class MyCustomViewDemoActivity extends Activity {
    /** Called when the activity is first created. */
	
	private MySoundView mySoundView;
	private int currentVol = 0;//当前音量
	private int maxVol = 0;
	private Context mContext;
	private boolean isVolButton = false;//判断是不是音量键被按下
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mySoundView = (MySoundView) findViewById(R.id.myid);
        mContext = this;
        currentVol = mySoundView.am.getStreamVolume(AudioManager.STREAM_MUSIC);
    }

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
			case KeyEvent.KEYCODE_VOLUME_DOWN:
				isVolButton = true;
				if(currentVol == 0) {
					Toast.makeText(mContext, "音量已经为0了，不能再减了", Toast.LENGTH_LONG).show();
					return true;
				}
				mySoundView.am.adjustStreamVolume(AudioManager.STREAM_MUSIC, 
						AudioManager.ADJUST_LOWER,
						AudioManager.FLAG_PLAY_SOUND);
				break;
				
			case KeyEvent.KEYCODE_VOLUME_UP:
				isVolButton = true;
				maxVol = mySoundView.getMaxVol();
				if(currentVol == maxVol) {
					Toast.makeText(mContext, "音量已经最大了", Toast.LENGTH_LONG).show();
					return true;
				}
				mySoundView.am.adjustStreamVolume(AudioManager.STREAM_MUSIC, 
						AudioManager.ADJUST_RAISE,
						AudioManager.FLAG_PLAY_SOUND);
				break;
	
			default:
				isVolButton = false;
				break;
		}
		if(isVolButton) {
			//设置当前音量
			currentVol = mySoundView.am.getStreamVolume(AudioManager.STREAM_MUSIC);
			mySoundView.setCurrentVol(currentVol);
			//重新绘制当前音量
			mySoundView.invalidate();
			isVolButton = false;
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}
}