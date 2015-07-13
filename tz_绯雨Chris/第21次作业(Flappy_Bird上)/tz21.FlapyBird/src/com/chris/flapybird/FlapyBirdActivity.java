package com.chris.flapybird;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

public class FlapyBirdActivity extends Activity implements SurfaceHolder.Callback
{
	private static final String tag = "FlapyBirdActivity";
	private SurfaceView sv;
	private FlapyDrawer birdDrawer;
	private Bitmap[] birdBmps;
	private int dispWidth;
	private int dispHeight;
	
	//鸟图片的id数组
	private int[] birdBmpRes = new int[]
			{ R.drawable.bird_1, R.drawable.bird_2, R.drawable.bird_3 };
	private FlapyDrawer birdDrawer2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flapy_bird);
		
		//获取屏幕的款和高
		WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics mainMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(mainMetrics);
		dispWidth = mainMetrics.widthPixels;
		dispHeight = mainMetrics.heightPixels;
		
		//初始化相关控件
		initViews();
	}

	private void initViews()
	{
		sv = (SurfaceView) findViewById(R.id.sv);
		sv.getHolder().addCallback(this);
//		sv.setOnClickListener(this);
		
		//设置鸟相关的动画图片
		initBirdBmps(birdBmpRes);
	}

	private void initBirdBmps(int[] birdBmpRes)
	{
		birdBmps = new Bitmap[birdBmpRes.length];
		for(int i=0; i<birdBmpRes.length; i++)
		{
			birdBmps[i] = BitmapFactory.decodeResource(getResources(), birdBmpRes[i]);
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder)
	{
		Log.d(tag, "surfaceCreated");
		//配置绘制鸟图片的画家对象
		birdDrawer = generateBirdDrawer(birdBmps, dispWidth/4, dispHeight/2, 50);
		
		//配置绘制背景的画家对象
		birdDrawer2 = generateBirdDrawer(birdBmps, dispWidth/2, dispHeight/4, 500);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
	{
		Log.d(tag, "surfaceChanged");
		birdDrawer.start();
		birdDrawer2.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder)
	{
		Log.d(tag, "surfaceDestroyed");
		birdDrawer.stopDraw();
		birdDrawer2.stopDraw();
	}
	
	/**
	 * 配置绘制鸟图片的画家对象
	 * @return
	 */
	private FlapyDrawer generateBirdDrawer(Bitmap[] bmps, int x, int y, int freq)
	{
		FlapyDrawer drawer = new FlapyDrawer(this, sv);
		drawer = new FlapyDrawer(this, sv);
		drawer.setmBitmaps(bmps);
		drawer.setBirdFlushFreq(freq);
		drawer.setBirdDrawPoint(new Point(x, y));
		return drawer;
	}

}
