package com.chris.flapybird.spirit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;

public class MySpirit
{
	protected static final String tag = "MySpirit";

	private static final int DEF_FLUSH_FREQ = 200;

	protected Context mContext;
	protected long mFlushFreq;
	protected int width, height;
	protected int xSpeed, ySpeed;
	protected Point mPoint;
	protected Canvas mCanvas;
	protected Bitmap[] mBitmaps;
	protected int bmpsIndex;

	/**
	 * 设置绘制图片的坐标位置
	 * @param point 需要绘制的Spirit的坐标点point位置
	 */
	public void setLocation(Point point)
	{
		this.mPoint.x = point.x;
		this.mPoint.y = point.y;
	}
	
	public Point getLocation()
	{
		return this.mPoint;
	}

	/**
	 * 设置重新Spirit绘制图像的刷新事件
	 * @param flushFreq 重新绘制Spirit图像的间隔时间，单位毫秒
	 */
	public void setFlushFreq(long flushFreq)
	{
		this.mFlushFreq = flushFreq;
	}

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

	public int getxSpeed()
	{
		return xSpeed;
	}

	public void setxSpeed(int xSpeed)
	{
		this.xSpeed = xSpeed;
	}

	public int getySpeed()
	{
		return ySpeed;
	}

	public void setySpeed(int ySpeed)
	{
		this.ySpeed = ySpeed;
	}

	/**
	 * 设置该Spirit的图片数组，确定该Spirit张什么样子
	 * @param bitmaps 该Spirit的图片数组
	 */
	public void setBitmaps(Bitmap[] bitmaps)
	{
		if (null != bitmaps)
		{
			this.mBitmaps = bitmaps;
			this.width = bitmaps[0].getWidth();
			this.height = bitmaps[0].getHeight();
		}
	}
	
	/**
	 * 获取该精灵的矩形
	 */
	public RectF getSpiritRect(){
		return new RectF(mPoint.x, mPoint.y, mPoint.x+this.width, mPoint.y+this.height);
	}

	/**
	 * 创建需要绘制的Spirit对象
	 * @param context 上下文
	 * @param bitmaps 该Spirit张什么样的图片数组
	 * @param x 该Spirit图像绘制的x坐标(左上角)
	 * @param y 该Spirit图像绘制的y坐标(右下角)
	 */
	public MySpirit(Context context, Bitmap[] bitmaps, int x, int y)
	{
		this.mContext = context;
		this.mPoint = new Point(x, y);
		if (null != bitmaps)
		{
			this.mBitmaps = bitmaps;
			this.width = bitmaps[0].getWidth();
			this.height = bitmaps[0].getHeight();
		}
		this.mFlushFreq = DEF_FLUSH_FREQ;
		this.bmpsIndex = 0;
		this.xSpeed = this.ySpeed = 0;
	}
	
	public MySpirit(Context context, Bitmap[] bitmaps)
	{
		this(context, bitmaps, 0, 0);
	}
	
	public MySpirit(Context context)
	{
		this(context, null);
	}
	
	public MySpirit cloneSelf()
	{
		MySpirit spirit = null;
		try
		{
			spirit = (MySpirit) this.clone();
		} catch (CloneNotSupportedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return spirit;
	}

	/**
	 * 绘制一帧画面
	 * 
	 * @param canvas
	 * @param startIndex
	 * @param flushTime
	 * @return true: 创建Runnable循环成功， false: 创建Runnable循环失败
	 */
	public void drawSelf(Canvas canvas)
	{
		canvas.drawBitmap(mBitmaps[bmpsIndex], this.mPoint.x, this.mPoint.y, null);
		this.mPoint.x += getxSpeed();
		this.mPoint.y += getySpeed();
		bmpsIndex++;
		if(bmpsIndex >= mBitmaps.length)
		{
			bmpsIndex = 0;
		}
	}
	
	public void drawSelfFromPoint(Canvas canvas, Point point)
	{
		canvas.drawBitmap(mBitmaps[bmpsIndex], point.x, point.y, null);
		mPoint.x = point.x;
		mPoint.y = point.y;
		bmpsIndex++;
		if(bmpsIndex >= mBitmaps.length)
		{
			bmpsIndex = 0;
		}
	}
	
	public void drawSelfFromRect(Canvas canvas, Rect src, Rect dest)
	{
		canvas.drawBitmap(mBitmaps[bmpsIndex], src, dest, null);
		mPoint.x = dest.left;
		mPoint.y = dest.top;
		bmpsIndex++;
		if(bmpsIndex >= mBitmaps.length)
		{
			bmpsIndex = 0;
		}
	}

}
