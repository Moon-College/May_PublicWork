/**
 * Project Name:activityTest
 * File Name:MainActivity.java
 * Package Name:com.zht.activitytest
 * Date:2015-3-27下午4:34:09
 * Copyright (c) 2015, shixi_hongtao@staff.sina.com.cn All Rights Reserved.
 *
 */

package com.zht.customview;

import com.zht.customview.widget.VolumeView;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * ClassName:MainActivity <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-3-27 下午4:34:09 <br/>
 * 
 * @author acer
 * @version
 * @since JDK 1.6
 * @see
 */
public class MainActivity extends Activity {
	private VolumeView volumeView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		volumeView = (VolumeView) findViewById(R.id.volumeView);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.i("INFO", "KeyEvent.KEYCODE_VOLUME_DOWN");
		if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
			volumeView.am.adjustStreamVolume(
					AudioManager.STREAM_MUSIC, 
					AudioManager.ADJUST_LOWER, 
					AudioManager.FLAG_PLAY_SOUND);
			//获取系统当前媒体音量
			volumeView.setStreamVolume(volumeView.am.getStreamVolume(AudioManager.STREAM_MUSIC));
			volumeView.invalidate();//重新调用ondraw方法进行重绘
			return true;
		}else if(keyCode == KeyEvent.KEYCODE_VOLUME_UP){
			volumeView.am.adjustStreamVolume(
					AudioManager.STREAM_MUSIC, 
					AudioManager.ADJUST_RAISE, 
					AudioManager.FLAG_PLAY_SOUND);
			//获取系统当前媒体音量
			volumeView.setStreamVolume(volumeView.am.getStreamVolume(AudioManager.STREAM_MUSIC));
			volumeView.invalidate();//重新调用ondraw方法进行重绘
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}
}
