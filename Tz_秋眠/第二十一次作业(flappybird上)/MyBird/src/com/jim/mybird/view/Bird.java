package com.jim.mybird.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * ����һֱ����Ӧ������
 * 
 * @author Jim
 * 
 */
public class Bird {
	private static final long SPAN_CUT_TIME = 50;// �л��ļ��ʱ��
	private float x;// ���X����
	private float y;// ���Y����

	private Bitmap bmp;// ���ͼƬ
	private Bitmap[] bmps;// ���ͼƬ����

	private int width;// ��Ŀ�
	private int height;// ��ĸ�

	private float xSpeed;// ��X������ٶ�
	private float ySpeed;// ��Y������ٶ�

	private int index;// ��ͼ����±�
	private long nowTime;// ��¼��ǰʱ��
	private long lastTime;// ��¼��һ�ε�ʱ��

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
	 * ÿ��һ��ʱ���л�һ��ͼƬ
	 * 
	 * @param span
	 *            �л�ͼƬ��ʱ��
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
	 * ���Լ�
	 * 
	 * @param canvas
	 */
	public void drawSelf(Canvas canvas) {
		cutBmp(SPAN_CUT_TIME);
		canvas.drawBitmap(bmp, x, y, null);
	}
}
