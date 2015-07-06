package com.tz.flappybird.may;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MainActivity extends Activity implements Callback {
	
	private SurfaceView surfaceView;  //画板
	private Drawer drawer; //画家

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//创建画板
		surfaceView = new SurfaceView(this);
		surfaceView.getHolder().addCallback(this);  //画板监听器
			
//		setContentView(R.layout.activity_main);  
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
}
