package com.hq.view;



import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;

public class Floor
{
	/*
	 * 地板位置游戏面板高度�?/5到底�?
	 */
	private static final float FLOOR_Y_POS_RADIO = 4 / 5F; // height of 4/5

	/**
	 * x坐标
	 */
	private int x;
	/**
	 * y坐标
	 */
	private int y;
	/**
	 * 填充�?
	 */
	private BitmapShader mFloorShader;

	private int mGameWidth;
	private int mGameHeight;

	public Floor(int gameWidth, int gameHeight, Bitmap floorBg)
	{
		mGameWidth = gameWidth;
		mGameHeight = gameHeight;
		y = (int) (gameHeight * FLOOR_Y_POS_RADIO);
		mFloorShader = new BitmapShader(floorBg, TileMode.REPEAT,
				TileMode.CLAMP);
	}

	/**
	 * 绘制自己
	 * 
	 * @param mCanvas
	 * @param mPaint
	 */
	public void draw(Canvas mCanvas, Paint mPaint)
	{
		if (-x > mGameWidth)
		{
			x = x % mGameWidth;
		}
		mCanvas.save(Canvas.MATRIX_SAVE_FLAG);
		//移动到指定的位置
		mCanvas.translate(x, y);
		mPaint.setShader(mFloorShader);
		mCanvas.drawRect(x, 0, -x + mGameWidth, mGameHeight - y, mPaint);
		mCanvas.restore();
		mPaint.setShader(null);
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}
	
	

}
