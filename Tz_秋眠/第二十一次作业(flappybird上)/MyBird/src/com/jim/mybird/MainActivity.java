package com.jim.mybird;

import com.jim.mybird.view.Drawer;

import android.app.Activity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MainActivity extends Activity implements Callback {
	private SurfaceView surfaceView;// 画板
	private Drawer drawer;// 画家

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		surfaceView = new SurfaceView(this);// 创建画板
		surfaceView.getHolder().addCallback(this);// 创建画板的监听器
		setContentView(surfaceView);
	}

	// 创建画板后
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		drawer = new Drawer(this, holder, surfaceView.getWidth(),
				surfaceView.getHeight());
		drawer.start();// 开启线程，让画家开始不断的工作
	}

	// 改变画板后
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	// 销毁画板后
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		drawer.setStart(false);// 让线程停止工作,让画家结束工作
	}
}