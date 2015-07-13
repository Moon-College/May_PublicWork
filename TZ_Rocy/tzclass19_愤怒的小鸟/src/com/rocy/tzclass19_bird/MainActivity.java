package com.rocy.tzclass19_bird;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class MainActivity extends Activity implements Callback, OnTouchListener {
	private  SurfaceView  surface ;
	private SurfaceHolder holder;
	private GameThread gameThread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		surface = new SurfaceView(this);
		//获得缓冲区
		holder = surface.getHolder();
		holder.addCallback(this);
		surface.setOnTouchListener(this);
		//游戏本身就是整个屏幕
		setContentView(surface);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// 画面创建
		 gameThread = new GameThread(surface.getContext(),surface.getWidth(),surface.getHeight(),holder);
		 gameThread.start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// 画面改变
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// 画面销毁
		gameThread.setDestory(true);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			switch (gameThread.getStatus()) {
			case GameThread.GAME_START:
				gameThread.setStatus(GameThread.GAME_RUNNING);
				break;
			case GameThread.GAME_RUNNING:
				if(!gameThread.isIsbirdDid()){
					//没死就可以操作
					Spirit bird = gameThread.getBird();
					bird.setY((int)(bird.getY()/(bird.getSpeed()*1.5)));
				}
				break;
			case GameThread.GAME_OVER:
				gameThread.setStatus(GameThread.GAME_START);
				break;

			default:
				break;
			}
			break;

		default:
			break;
		}
		return false;
	}
}
