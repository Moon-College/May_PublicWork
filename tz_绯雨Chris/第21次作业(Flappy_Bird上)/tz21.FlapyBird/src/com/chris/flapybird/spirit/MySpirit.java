package com.chris.flapybird.spirit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

public abstract class MySpirit
{
	protected static final String tag = "MySpirit";

	protected int width, height;
	protected int left, top; //spirit的绘制坐标
	protected Context mContext;
	protected Canvas mCanvas;
	protected Bitmap[] mBitmaps;
	
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

	public MySpirit(Context context)
	{
		this(context, null);
	}

	public MySpirit(Context context, Bitmap[] bitmaps)
	{
		this(context, bitmaps, 0, 0);
	}

	public MySpirit(Context context, Bitmap[] bitmaps, int x, int y)
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
	public abstract int startDrawFrame(Canvas canvas, int startIndex, int flushTime);

}
