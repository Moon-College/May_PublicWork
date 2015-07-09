/**
 * Project Name:flappy_bird
 * File Name:Spirit.java
 * Package Name:com.zht.flappybird.bean
 * Date:2015-7-6����3:45:48
 * Copyright (c) 2015, shixi_hongtao@staff.sina.com.cn All Rights Reserved.
 *
 */

package com.zht.flappybird.bean;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * ClassName:Spirit <br/>
 * Function: ����. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-7-6 ����3:45:48 <br/>
 * 
 * @author acer
 * @version
 * @since JDK 1.6
 * @see
 */
public class Spirit implements Cloneable{
	private float x;
	private float y;// ����� xy ����
	private Bitmap bitmap;
	private Bitmap[] bitmaps;// ����ͼƬ
	private int width;
	private int height;// ����Ŀ��
	private float XSpeed;
	private float YSpped;// ����xy������ٶ�
	private int index;
	private long lastTime;
	public static final long INTERVAL_TIME = 50;

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

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
		this.width = bitmap.getWidth();
		this.height = bitmap.getHeight();
	}

	public Bitmap[] getBitmaps() {
		return bitmaps;
	}

	public void setBitmaps(Bitmap[] bitmaps) {
		this.bitmaps = bitmaps;
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

	public float getXSpeed() {
		return XSpeed;
	}

	public void setXSpeed(float xSpeed) {
		XSpeed = xSpeed;
	}

	public float getYSpped() {
		return YSpped;
	}

	public void setYSpped(float ySpped) {
		YSpped = ySpped;
	}

	public Spirit(Bitmap bitmap) {
		this.bitmap = bitmap;
		this.width = bitmap.getWidth();
		this.height = bitmap.getHeight();
	}

	public Spirit(Bitmap[] bitmaps) {
		bitmap = bitmaps[0];
		this.bitmaps = bitmaps;
		this.width = bitmap.getWidth();
		this.height = bitmap.getHeight();
	}

	// ÿ��һ��ʱ���л�һ��ͼƬ
	private void showNextBitmap(long span) {
		if (bitmaps == null || bitmaps.length == 0) {
			return;
		}
		bitmap = bitmaps[index];
		if (System.currentTimeMillis() - lastTime > span) {
			index++;
			if (index >= bitmaps.length) {
				index = 0;
			}
			lastTime = System.currentTimeMillis();
		}
	}

	// �����Լ�
	public void drawSelf(Canvas canvas) {
		showNextBitmap(INTERVAL_TIME);
		canvas.drawBitmap(bitmap, x, y, null);
	}
	
	/**
	 * ��¡�Լ�
	 */
	public Spirit getCloneObject(){
		try {
			return (Spirit) this.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// ��ȡ�þ���ľ���
	public RectF getSpiritRect() {
		return new RectF(this.getX(), this.getY(), this.getX()
				+ this.getWidth(), this.getY() + this.getHeight());
	}
}
