package com.jim.mybird.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * 定义一直鸟相应的属性
 * 
 * @author Jim
 * 
 */
public class Bird {
	private static final long SPAN_CUT_TIME = 50;// 切换的间隔时间
	private float x;// 鸟的X坐标
	private float y;// 鸟的Y坐标

	private Bitmap bmp;// 鸟的图片
	private Bitmap[] bmps;// 鸟的图片数组

	private int width;// 鸟的宽
	private int height;// 鸟的高

	private float xSpeed;// 鸟X方向的速度
	private float ySpeed;// 鸟Y方向的速度

	private int index;// 鸟图标的下标
	private long nowTime;// 记录当前时间
	private long lastTime;// 记录上一次的时间

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Bitmap getBmp() {
		return bmp;
	}

	public void setBmp(Bitmap bmp) {
		this.bmp = bmp;
	}

	public Bitmap[] getBmps() {
		return bmps;
	}

	public void setBmps(Bitmap[] bmps) {
		this.bmps = bmps;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(float xSpeed) {
		this.xSpeed = xSpeed;
	}

	public float getySpeed() {
		return ySpeed;
	}

	public void setySpeed(float ySpeed) {
		this.ySpeed = ySpeed;
	}

	public Bird(Bitmap[] bmps) {
		this.bmps = bmps;
		this.bmp = bmps[0];
		this.width = bmp.getWidth();
		this.height = bmp.getHeight();
	}

	public Bird(Bitmap bmp) {
		this.bmp = bmp;
		this.width = bmp.getWidth();
		this.height = bmp.getHeight();
	}

	/**
	 * 每隔一段时间切换一次图片
	 * 
	 * @param span
	 *            切换图片的时间
	 */
	public void cutBmp(long span) {
		if (bmps != null && bmps.length != 0) {
			nowTime = System.currentTimeMillis();
			bmp = bmps[index];
			if (nowTime - lastTime >= span) {
				index++;
				if (index >= bmps.length) {
					index = 0;
				}
				lastTime = nowTime;
			}
		}
	}

	/**
	 * 画自己
	 * 
	 * @param canvas
	 */
	public void drawSelf(Canvas canvas) {
		cutBmp(SPAN_CUT_TIME);
		canvas.drawBitmap(bmp, x, y, null);
	}
}
