package com.chris.flapybird;

import com.chris.flapybird.spirit.SpiritBird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class FlapyDrawer extends Thread
{
	private static final String tag = "FlapyDrawer";
	
	/**
	 * 通用属性
	 */
	private SurfaceView mSurView;
	private SurfaceHolder mHolder;
	private Canvas mCanvas;
	private Context mContext;
	private Bitmap[] mBitmaps;
	
	/**
	 * 鸟的属性
	 */
	private static final int BIRD_DEF_X = 300;
	private static final int BIRD_DEF_Y = 200;
	private static final int BIRD_FLUSH_FREQ = 100;
	private int birdFlushFreq = BIRD_FLUSH_FREQ;
	private int birdX, birdY;
	private SpiritBird mBird;
	private int birdBmpIndex = 0;
	
	/**
	 * Handler和Runnable，循环主体
	 */
	private Handler mHandler;
	private Runnable mRunnable = new Runnable()
	{

		@Override
		public void run()
		{
			mHandler.removeCallbacks(mRunnable);
			mCanvas = mHolder.lockCanvas();
			try
			{
				if (mBitmaps.length>0 && null != mCanvas && null != mBird)
				{
					int resultIndex = mBird.startDrawFrame(mCanvas, birdBmpIndex, birdFlushFreq);
					birdBmpIndex = resultIndex;
					if (birdBmpIndex >= mBitmaps.length)
					{
						birdBmpIndex = 0;
					}
				}
				else
				{
					Log.e(tag, "mBitmaps.length ="+mBitmaps.length);
					Log.e(tag, "mCanvas =" + mCanvas + " mBird=" + mBird);
				}

			} catch (Exception e)
			{
				
			} finally
			{
				mHolder.unlockCanvasAndPost(mCanvas);
			}
			mHandler.postDelayed(mRunnable, birdFlushFreq);
		}
	};
	
	/**
	 * 设置鸟的图片的刷新频率
	 * @param flushFreq
	 */
	public void setBirdFlushFreq(int flushFreq)
	{
		this.birdFlushFreq = flushFreq;
	}
	
	/**
	 * 设置要绘画的图片数组
	 * @param mBitmaps
	 */
	public void setmBitmaps(Bitmap[] mBitmaps)
	{
		this.mBitmaps = mBitmaps;
	}
	
	/**
	 * 设置绘制鸟的图片的坐标位置
	 * @param point
	 */
	public void setBirdDrawPoint(Point point)
	{
		this.birdX = point.x;
		this.birdY = point.y;
	}
	
	public FlapyDrawer(Context context, SurfaceView surView)
	{
		this(context, surView, BIRD_DEF_X, BIRD_DEF_Y);
	}

	public FlapyDrawer(Context context, SurfaceView surView, int x, int y)
	{
		this(context, surView, BIRD_FLUSH_FREQ, null, x, y);
	}
	
	public FlapyDrawer(Context context, SurfaceView surView, Bitmap[] bitmaps)
	{
		this(context, surView, BIRD_FLUSH_FREQ, bitmaps, BIRD_DEF_X, BIRD_DEF_Y);
	}

	public FlapyDrawer(Context context, SurfaceView surView, int flushTime, Bitmap[] bitmaps, int x, int y)
	{
		mHandler = new Handler();
		this.mContext = context;
		this.mSurView = surView;
		this.mHolder = mSurView != null ? mSurView.getHolder() : null;
		this.birdX = x;
		this.birdY = y;
		this.birdFlushFreq = flushTime;
		this.mBitmaps = bitmaps;
	}

	/**
	 * 开始在循环中绘图
	 */
	@Override
	public void run()
	{
		mBird = new SpiritBird(mContext, mBitmaps, birdX, birdY);
		if (this.mHandler != null)
		{
			mHandler.postDelayed(mRunnable, birdFlushFreq);
		}
		super.run();
	}

	/**
	 * 停止绘图，退出Runnable循环
	 */
	public void stopDraw()
	{
		mHandler.removeCallbacks(mRunnable);
	}
}
