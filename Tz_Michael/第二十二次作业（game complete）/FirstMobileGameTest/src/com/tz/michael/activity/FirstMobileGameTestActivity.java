package com.tz.michael.activity;

import com.tz.michael.utils.Drawer;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class FirstMobileGameTestActivity extends Activity implements Callback, OnTouchListener {
    private SurfaceView surfaceView;
	private Drawer drawer;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        surfaceView = new SurfaceView(this);
        surfaceView.getHolder().addCallback(this);
        surfaceView.setOnTouchListener(this);
        setContentView(surfaceView);
    }

	public void surfaceCreated(SurfaceHolder holder) {
		drawer = new Drawer(this, holder, surfaceView.getWidth(), surfaceView.getHeight());
		drawer.start();
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		drawer.setStart(false);
	}

	public boolean onTouch(View v, MotionEvent event) {
		switch (drawer.getStatus()) {
		case Drawer.GAMEMENU:
			//ready界面点击屏幕，开始游戏
			drawer.setStatus(Drawer.GAMEPLAYING);
			break;
		case Drawer.GAMEPLAYING:
			//正在运行状态，鸟弹
			if(!drawer.isDrop()){
				drawer.getBird().setSpeedY(-15);
			}
			break;
		case Drawer.GAMEOVER:
			drawer.setStatus(Drawer.GAMEMENU);
			drawer.initAttrs();
			break;
		default:
			break;
		}
		return false;
	}
}