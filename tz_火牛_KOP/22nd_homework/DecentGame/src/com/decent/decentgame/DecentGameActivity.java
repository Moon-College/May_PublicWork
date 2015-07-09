package com.decent.decentgame;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

import com.decent.decentgame.bean.Spirit;
import com.decent.decentgame.util.Drawer;

public class DecentGameActivity extends Activity implements Callback, OnTouchListener {
	/**
	 * ����-----һ����SurfaceView
	 */
	private SurfaceView mSurfaceView;
	/**
	 * ����-----һ����thread��ͣ�Ļ���ͼƬ
	 */
	private Drawer mDrawer;
	
	private Spirit bg2;
	private Spirit floor;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    	mSurfaceView = new SurfaceView(this);
    	/*
    	 * surfaceview�Ǹ����ܵģ���Ҫ��ʼ�����氡ʲô�ĵģ����Գ�ʼ������Ҫһ��ʱ��
    	 * ��Ҫ��һ���ص����������״̬�仯������Ӧ�Ĳ���
    	 * getHolder�ǵõ����Ĺܼ�
    	 */
    	mSurfaceView.getHolder().addCallback(this);
    	//SurfaceViewĬ�Ͽ�͸߶���fill_parent
        setContentView(mSurfaceView);
        mSurfaceView.setOnTouchListener(this);
    }
    
    /**
     * This is called immediately after the surface is first created.
     * �������ʵ��surfaview�ĵ�һ�γ�ʼ����ɵ��õ��ĺ���
     */
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		//����Drawer�߳�
		mDrawer = new Drawer(this,holder,mSurfaceView.getWidth(),mSurfaceView.getHeight());
		mDrawer.start();
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.d("INFO", "...surfaceDestroyed...");
		//surfcaeView���ٵ�ʱ����Ҫ���߳��˳�
		mDrawer.setStarted(false);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (mDrawer.getGameStatus()) {
		case Drawer.READY_STATE:
			mDrawer.setGameStatus(Drawer.RUNNING_STATE);
			break;
		case Drawer.RUNNING_STATE:
			if(!mDrawer.isBirdDropped()){
				mDrawer.getBirdSpirit().setySpeed(-15);
			}
			break;
		case Drawer.OVER_STATE:
			mDrawer.setGameStatus(Drawer.READY_STATE);
			mDrawer.initAttrs();			
			break;
		default:
			break;
		}
		// TODO Auto-generated method stub
		return false;
	}
    
    
}