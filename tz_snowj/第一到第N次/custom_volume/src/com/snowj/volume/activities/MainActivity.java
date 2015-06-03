package com.snowj.volume.activities;

import java.io.IOException;

import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.snowj.volume.BaseActivity;
import com.snowj.volume.R;
import com.snowj.volume.utils.CustomLog;
import com.snowj.volume.widget.CustomVolumeView;

public class MainActivity extends BaseActivity {

	private Button btn1;
	private CustomVolumeView volume_view;
	private Button btn2;
	private Button btn3;
	private Button btn4;
	private final static int Request_Code = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getView();
		initView();
	}
	
	/**  获取控件 */
	private void getView(){
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn3 = (Button) findViewById(R.id.btn3);
		btn4 = (Button) findViewById(R.id.btn4);
		volume_view = (CustomVolumeView)findViewById(R.id.volume_view);
	}
	
	/**初始化控件*/
	private void initView() {
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
	}
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		/**
		 * 第八次作业
		 * 重写加减音量键 事件
		 */
		if(keyCode==KeyEvent.KEYCODE_VOLUME_DOWN){
			volume_view.audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
			volume_view.setCurrentVolume(volume_view.audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
			volume_view.invalidate();
			return true;
		}else if(keyCode == KeyEvent.KEYCODE_VOLUME_UP){
			volume_view.audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
			volume_view.setCurrentVolume(volume_view.audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
			volume_view.invalidate();
			return true;
		}
		
		return super.onKeyDown(keyCode, event);
		
	}
	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn1:
			//跳转搜索界面---第三,四次作业
			Intent intent = new Intent();
			intent.setAction(".activities.SearchActivity");
			startActivityForResult(intent, Request_Code);
			break;
		case R.id.btn3:
			//跳转搜索界面---第五次作业
			Intent intent1 = new Intent(this,ListViewActivity.class);
			startActivity(intent1);
			break;
		case R.id.btn4:
			//跳转搜索界面---第六 ,七次作业
			Intent intent2 = new Intent(this,FileActivity.class);
			startActivity(intent2);
			break;
		case R.id.btn2:
			//第二次作业
			try {
				new CustomLog().readLogFromRunntime(this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		default:
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode==RESULT_OK&&requestCode==Request_Code) {
			
		}
	}
	
	
}
