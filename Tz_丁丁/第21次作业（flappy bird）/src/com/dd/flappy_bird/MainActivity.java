package com.dd.flappy_bird;

import com.dd.flappy_bird.drawer.Drawer;

import android.app.Activity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MainActivity extends Activity implements Callback {
	private SurfaceView surfaceView;//画板
	private Drawer drawer;//画家
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		surfaceView = new SurfaceView(this);
		surfaceView.getHolder().addCallback(this);
		setContentView(surfaceView);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		//画板被创建好了，创建画家准备画画了
		drawer = new Drawer(this, holder, surfaceView.getWidth(), surfaceView.getHeight());
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
	}


}
