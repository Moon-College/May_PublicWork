package com.jim.mybird;

import com.jim.mybird.view.Drawer;

import android.app.Activity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MainActivity extends Activity implements Callback {
	private SurfaceView surfaceView;// ����
	private Drawer drawer;// ����

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		surfaceView = new SurfaceView(this);// ��������
		surfaceView.getHolder().addCallback(this);// ��������ļ�����
		setContentView(surfaceView);
	}

	// ���������
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		drawer = new Drawer(this, holder, surfaceView.getWidth(),
				surfaceView.getHeight());
		drawer.start();// �����̣߳��û��ҿ�ʼ���ϵĹ���
	}

	// �ı仭���
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	// ���ٻ����
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		drawer.setStart(false);// ���߳�ֹͣ����,�û��ҽ�������
	}
}