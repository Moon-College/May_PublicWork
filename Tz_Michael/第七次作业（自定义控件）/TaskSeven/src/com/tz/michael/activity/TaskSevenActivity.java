package com.tz.michael.activity;

import com.tz.michael.customview.SoundView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TaskSevenActivity extends Activity implements OnClickListener {
    private SoundView sv;
    private Button btn;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        sv=(SoundView) findViewById(R.id.sv);
        btn=(Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//    	AudioManager am=(AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
//    	int cur_vol=am.getStreamVolume(AudioManager.STREAM_RING);
    	switch (keyCode) {
		case KeyEvent.KEYCODE_VOLUME_DOWN:
			sv.am.adjustStreamVolume(AudioManager.STREAM_RING,
					AudioManager.ADJUST_LOWER,
					AudioManager.FLAG_PLAY_SOUND);
			sv.setCurVol(sv.am.getStreamVolume(AudioManager.STREAM_RING));
			sv.invalidate();
			return true;
		case KeyEvent.KEYCODE_VOLUME_UP:
			sv.am.adjustStreamVolume(AudioManager.STREAM_RING,
					AudioManager.ADJUST_RAISE,
					AudioManager.FLAG_PLAY_SOUND);
			sv.setCurVol(sv.am.getStreamVolume(AudioManager.STREAM_RING));
			sv.invalidate();
			return true;
		default:
			break;
		}
    	return super.onKeyDown(keyCode, event);
    }

	public void onClick(View v) {
		//测试改变颜色
		sv.setValueColor(Color.BLUE);
		//测试改变宽高
		sv.setRect_width(200);
		sv.setRect_height(40);
		
	}
}