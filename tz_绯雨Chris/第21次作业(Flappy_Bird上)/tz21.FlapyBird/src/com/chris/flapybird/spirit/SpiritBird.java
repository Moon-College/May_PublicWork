package com.chris.flapybird.spirit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

public class SpiritBird
{
	protected static final String tag = "SpiritBird";

	private int width, height;
	private int left, top; //spirit的绘制坐标
	private Context mContext;
	private Canvas mCanvas;
	private Bitmap[] mBitmaps;
	
	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public void setBitmaps(Bitmap[] bitmaps)
	{
		this.mBitmaps = bitmaps;
	}

	public SpiritBird(Context context)
	{
		this(context, null);
	}

	public SpiritBird(Context context, Bitmap[] bitmaps)
	{
		this(context, bitmaps, 0, 0);
	}

	public SpiritBird(Context context, Bitmap[] bitmaps, int x, int y)
	{
		this.mContext = context;
		this.left = x;
		this.top = y;
		this.mBitmaps = bitmaps;
		this.width = bitmaps[0].getWidth();
		this.height = bitmaps[0].getHeight();
	}

	/**
	 * 绘制一帧图像
	 * @param canvas
	 * @param startIndex
	 * @param flushTime
	 * @return
	 */
	public int startDrawFrame(Canvas canvas, int startIndex, int flushTime)
	{
		this.mCanvas = canvas;
		if (null != mBitmaps[startIndex])
		{
			canvas.drawBitmap(mBitmaps[startIndex], left, top, null);
		} else
		{
			Log.e(tag, "draw bitmaps[" + startIndex + "] failed of null bitmap");
		}
		return startIndex + 1;
	}

}
