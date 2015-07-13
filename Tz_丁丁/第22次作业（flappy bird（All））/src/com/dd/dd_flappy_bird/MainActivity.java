package com.dd.dd_flappy_bird;

import com.dd.dd_flappy_bird.drawer.Drawer;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class MainActivity extends Activity implements Callback, OnTouchListener {
	private SurfaceView surfaceView;//画板
    private Drawer drawer;//画家
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		surfaceView = new SurfaceView(this);//创建画板
        surfaceView.getHolder().addCallback(this);//画板监听器
        surfaceView.setOnTouchListener(this);
        setContentView(surfaceView);
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		//画板被创建好了，创建画家准备画画了
		drawer = new Drawer(this,holder,surfaceView.getWidth(),surfaceView.getHeight());
    	drawer.start();
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		//画板改变了
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		//画板销毁了
		drawer.setStart(false);
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (drawer.getGameStatu()) {
		case Drawer.READY:
			//ready界面点击屏幕，开始游戏
			drawer.setGameStatu(Drawer.RUNNING);
			break;
		case Drawer.RUNNING:
			//正在运行状态，鸟弹
			if(!drawer.isDrop()){
				drawer.getBird().setySpeed(-15);
			}
			break;
		case Drawer.OVER:
			drawer.setGameStatu(Drawer.READY);
			drawer.initAttrs();
			break;
		default:
			break;
		}
		return false;
	}

}
