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
	 * ����-----һ����SurfaceView
	 */
	private SurfaceView mSurfaceView;
	/**
	 * ����-----һ����thread��ͣ�Ļ���ͼƬ
	 */
	private Drawer mDrawer;
	/**
	 * С����----ͼƬ
	 */
	private Spirit mSpirit;
	/**
	 * Ҫ��ʾ��ͼƬ�б�
	 */
	private int[] bmp_paths = {
			R.drawable.bird_1,
			R.drawable.bird_2,
			R.drawable.bird_3
	};
	/**
	 * ͼƬ��bitmap�б�
	 */
	private Bitmap[] bmps;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //��ʼ��ͼƬ
        bmps = new Bitmap[bmp_paths.length];
        for(int i = 0;i<bmp_paths.length;i++)
        {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), bmp_paths[i]);
            bmps[i] = bitmap;
        }
        
    	mSurfaceView = new SurfaceView(this);
    	/*
    	 * surfaceview�Ǹ����ܵģ���Ҫ��ʼ�����氡ʲô�ĵģ����Գ�ʼ������Ҫһ��ʱ��
    	 * ��Ҫ��һ���ص����������״̬�仯������Ӧ�Ĳ���
    	 * getHolder�ǵõ����Ĺܼ�
    	 */
    	mSurfaceView.getHolder().addCallback(this);
    	//SurfaceViewĬ�Ͽ�͸߶���fill_parent
        setContentView(mSurfaceView);
    }
    
    /**
     * This is called immediately after the surface is first created.
     * �������ʵ��surfaview�ĵ�һ�γ�ʼ����ɵ��õ��ĺ���
     */
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		//��ʼ��Spirit
		mSpirit = new Spirit(200, 200, bmps);
		//����Drawer�߳�
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
		//surfcaeView���ٵ�ʱ����Ҫ���߳��˳�
		mDrawer.setStarted(false);
	}
    
    
}