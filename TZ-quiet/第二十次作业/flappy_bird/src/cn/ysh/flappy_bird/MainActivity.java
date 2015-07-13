package cn.ysh.flappy_bird;

import android.os.Bundle;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class MainActivity extends Activity implements Callback, OnTouchListener {
	private SurfaceView surfaceView;
	private Drawer drawer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		surfaceView = new SurfaceView(this);
		surfaceView.getHolder().addCallback(this);
		surfaceView.setOnTouchListener(this);
		setContentView(surfaceView);
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		drawer = new Drawer(this, holder, surfaceView.getWidth(), surfaceView.getHeight());
		drawer.start();
		
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		drawer.setStart(false);
		
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (drawer.getGameStatus()) {
		case Drawer.READY:
			drawer.setGameStatus(Drawer.RUNNING);
			break;
		case Drawer.RUNNING:
			if(!drawer.isDrop){
				drawer.getBird().setySpeed(-15);
			}
			break;
		case Drawer.OVER:
			drawer.setGameStatus(Drawer.READY);
			drawer.initSpiritAttrs();
			break;

		default:
			break;
		}
		return false;
	}
	
	

}
