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
	private SurfaceView surfaceView;//����
    private Drawer drawer;//����
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		surfaceView = new SurfaceView(this);//��������
        surfaceView.getHolder().addCallback(this);//���������
        surfaceView.setOnTouchListener(this);
        setContentView(surfaceView);
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		//���屻�������ˣ���������׼��������
		drawer = new Drawer(this,holder,surfaceView.getWidth(),surfaceView.getHeight());
    	drawer.start();
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		//����ı���
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		//����������
		drawer.setStart(false);
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (drawer.getGameStatu()) {
		case Drawer.READY:
			//ready��������Ļ����ʼ��Ϸ
			drawer.setGameStatu(Drawer.RUNNING);
			break;
		case Drawer.RUNNING:
			//��������״̬����
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
