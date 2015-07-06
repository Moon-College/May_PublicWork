package com.tz.flappybird.may;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MainActivity extends Activity implements Callback {
	
	private SurfaceView surfaceView;  //����
	private Drawer drawer; //����

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//��������
		surfaceView = new SurfaceView(this);
		surfaceView.getHolder().addCallback(this);  //���������
			
//		setContentView(R.layout.activity_main);  
		setContentView(surfaceView);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		//���崴������,��������,׼������
		drawer = new Drawer(this, holder, surfaceView.getWidth(), surfaceView.getHeight());
		drawer.start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		//����ı�
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		//��������
		drawer.setStart(false);
	}
}
