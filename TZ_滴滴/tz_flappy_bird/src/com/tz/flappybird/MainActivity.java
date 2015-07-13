package com.tz.flappybird;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class MainActivity extends Activity implements Callback, OnTouchListener {
	private SurfaceView surfaceView;  //画板
	private Drawer drawer;   //画家
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
		// TODO Auto-generated method stub
		
	}
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		drawer.setStart(false);
	}
	public boolean onTouch(View v, MotionEvent event) {
		
		switch (drawer.getGameStatu()) {
		case Drawer.READY:
			//开始game
			drawer.setGameStatu(Drawer.RUNNING);
			break;
		case Drawer.RUNNING:
			//drawer.setGameStatu(Drawer.OVER);
			//正在运行状态，鸟弹
			if (!drawer.isDrop()) {  //先判断鸟是否正在下坠
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
	
//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		// TODO Auto-generated method stub
//		//return super.onTouchEvent(event);
//		//drawer.setStart(false);
//		//surfaceView.clearAnimation();
//		drawer.getBird().setX(event.getX());
//		drawer.getBird().setY(event.getY());
//		return true;
//	}
	
}