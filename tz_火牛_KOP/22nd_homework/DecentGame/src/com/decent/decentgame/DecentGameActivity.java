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
	 * 画布-----一般是SurfaceView
	 */
	private SurfaceView mSurfaceView;
	/**
	 * 画家-----一般是thread不停的绘制图片
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
    	 * surfaceview是高性能的，需要初始化缓存啊什么的的，所以初始化还需要一定时间
    	 * 需要挂一个回调来检测它的状态变化来做相应的操作
    	 * getHolder是得到它的管家
    	 */
    	mSurfaceView.getHolder().addCallback(this);
    	//SurfaceView默认宽和高都是fill_parent
        setContentView(mSurfaceView);
        mSurfaceView.setOnTouchListener(this);
    }
    
    /**
     * This is called immediately after the surface is first created.
     * 这个函数实在surfaview的第一次初始化完成调用到的函数
     */
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		//开启Drawer线程
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
		//surfcaeView销毁的时候需要把线程退出
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