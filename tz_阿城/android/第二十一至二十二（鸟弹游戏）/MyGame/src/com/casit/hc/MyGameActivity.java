package com.casit.hc;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class MyGameActivity extends Activity implements Callback, OnTouchListener {
    /** Called when the activity is first created. */
    private SurfaceView surfaceView;
    private Drawer drawer;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        surfaceView = new SurfaceView(this);
        surfaceView.getHolder().addCallback(this);
        surfaceView.setOnTouchListener(this);
        setContentView(surfaceView);
        
    }
	public void surfaceCreated(SurfaceHolder holder) {
		// 画板创建
		drawer = new Drawer(this,holder,surfaceView.getWidth(),surfaceView.getHeight());
		drawer.start();
	}
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		//画板改变了
		
	}
	public void surfaceDestroyed(SurfaceHolder holder) {
		// 画板销毁了
		drawer.setStart(false);
	}
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		
		switch (drawer.getGameStatu()){
		case Drawer.READY:
			drawer.setGameStatu(Drawer.RUNNING);
			break;
		case Drawer.RUNNING:
			if(!drawer.isDrop()){
		    	drawer.getBird().setSpeedY(-15);
			}
			break;
		case Drawer.OVER:
			drawer.setGameStatu(Drawer.READY);
			drawer.initAttrs();
			break;

		default:
			break;
		}
		return true;
	}
}