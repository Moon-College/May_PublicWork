package myvolume;

import com.example.myvolume.R;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;

public class MainActivity extends Activity {
	private MyVolumeView myvolume;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		myvolume = (MyVolumeView) findViewById(R.id.myvolume);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		//截取两个按钮事件
		switch (keyCode) {
		case KeyEvent.KEYCODE_VOLUME_DOWN:
			myvolume.mAudioManager.adjustStreamVolume(
					AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER,
					AudioManager.FLAG_PLAY_SOUND);
			doInvalidate();
			return true;
		case KeyEvent.KEYCODE_VOLUME_UP:
			myvolume.mAudioManager.adjustStreamVolume(
					AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE,
					AudioManager.FLAG_PLAY_SOUND);
			doInvalidate();
			return true;
		default:
			//其他事件顺利通过
			return super.onKeyDown(keyCode, event);
		}
		
	}

	private void doInvalidate() {
		myvolume.setmCurrentVolume(myvolume.mAudioManager
				.getStreamVolume(AudioManager.STREAM_MUSIC));
		myvolume.invalidate();		
	}
}
