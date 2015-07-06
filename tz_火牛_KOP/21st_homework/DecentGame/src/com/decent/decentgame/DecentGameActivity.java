package com.decent.decentgame;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import com.decent.decentgame.bean.Spirit;
import com.decent.decentgame.util.Drawer;

public class DecentGameActivity extends Activity implements Callback {
	/**
	 * 画布-----一般是SurfaceView
	 */
	private SurfaceView mSurfaceView;
	/**
	 * 画家-----一般是thread不停的绘制图片
	 */
	private Drawer mDrawer;
	/**
	 * 小精灵----图片
	 */
	private Spirit mSpirit;
	/**
	 * 要显示的图片列表
	 */
	private int[] bmp_paths = {
			R.drawable.bird_1,
			R.drawable.bird_2,
			R.drawable.bird_3
	};
	/**
	 * 图片的bitmap列表
	 */
	private Bitmap[] bmps;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //初始化图片
        bmps = new Bitmap[bmp_paths.length];
        for(int i = 0;i<bmp_paths.length;i++)
        {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), bmp_paths[i]);
            bmps[i] = bitmap;
        }
        
    	mSurfaceView = new SurfaceView(this);
    	/*
    	 * surfaceview是高性能的，需要初始化缓存啊什么的的，所以初始化还需要一定时间
    	 * 需要挂一个回调来检测它的状态变化来做相应的操作
    	 * getHolder是得到它的管家
    	 */
    	mSurfaceView.getHolder().addCallback(this);
    	//SurfaceView默认宽和高都是fill_parent
        setContentView(mSurfaceView);
    }
    
    /**
     * This is called immediately after the surface is first created.
     * 这个函数实在surfaview的第一次初始化完成调用到的函数
     */
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		//初始化Spirit
		mSpirit = new Spirit(200, 200, bmps);
		//开启Drawer线程
		mDrawer = new Drawer(this,mSpirit,holder);
		mDrawer.start();
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		//surfcaeView销毁的时候需要把线程退出
		mDrawer.setStarted(false);
	}
    
    
}