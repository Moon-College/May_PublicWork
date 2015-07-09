package com.tz.flappybird.may;

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
	
	private SurfaceView surfaceView;  //画板
	private Drawer drawer; //画家

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);  
		
		//创建画板
		surfaceView = new SurfaceView(this);
		surfaceView.getHolder().addCallback(this);  //画板监听器
			
		surfaceView.setOnTouchListener(this);
		setContentView(surfaceView);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		//画板创建好了,创建画家,准备画画
		drawer = new Drawer(this, holder, surfaceView.getWidth(), surfaceView.getHeight());
		drawer.start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		//画板改变
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		//画板销毁
		drawer.setStart(false);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (drawer.getGameStatus()) {
		case Drawer.READY:
			//ready界面点击游泳,开始游戏
			drawer.setGameStatus(Drawer.RUNNING);
			break;
		case Drawer.RUNNING:
			//正在运行状态,鸟动起来
			if(!drawer.isDrop()){
				drawer.getBird().setySpeed(-15); //向上为负
			}
			break;
		case Drawer.OVER:
			drawer.setGameStatus(Drawer.READY);
			drawer.initAttrs();
			break;

		default:
			break;
		}
		return false;
	}
}
