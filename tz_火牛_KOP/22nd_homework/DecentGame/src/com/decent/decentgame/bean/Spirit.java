package com.decent.decentgame.bean;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

public class Spirit implements Cloneable {
	/**
	 * ���ŵ�ʱ��������λms
	 */
	public static final int PALY_INTERVAL = 50;
	/**
	 * �ϴβ��ŵ�ʱ��
	 */
	private long lastPlayTime;
	/**
	 * x����
	 */
	private float x;
	/**
	 * y����
	 */
	private float y;
	/**
	 * ͼƬ���
	 */
	private float width;
	/**
	 * ͼƬ�߶�
	 */
	private float height;
	/**
	 * ��ǰ���ŵ�ͼƬ��index
	 */
	private int currentBmpIndex;
	/**
	 * ��ǰ���ŵ�ͼƬ
	 */
	private Bitmap bitmap;
	/**
	 * ��Ҫ���ŵ�ͼƬ���б�
	 */
	private Bitmap[] bmps;

	/**
	 * x�����ϵ��ٶ�
	 */
	private float xSpeed;
	/**
	 * y�����ϵ��ٶ�
	 */
	private float ySpeed;

	/**
	 * ����ͼƬ����
	 */
	private float scale;
	/**
	 * ���캯��
	 * 
	 * @param x
	 *            ͼƬ��x����
	 * @param y
	 *            ͼƬ��y����
	 * @param bmps
	 *            Ҫ���ŵ�ͼƬ�б�bmps�ĵ�һ�ž���Ҫ���ŵĵ�һ��
	 */
	public Spirit(float x, float y, Bitmap[] bmps) {
		this.x = x;
		this.y = y;
		this.bmps = bmps;
		// Ĭ��ʹ��0��Ϊ��һ��
		currentBmpIndex = 0;
		this.bitmap = this.bmps[currentBmpIndex];
		// ��ȡͼƬ�Ŀ�͸�
		this.width = this.bitmap.getWidth();
		this.height = this.bitmap.getHeight();
		/*
		 * lastPlayTimeĬ�Ͼ���0����һ�����е�playNextBmp��ʱ��
		 * �Ǹ�ʱ���ж���Ȼ�ܹ�����֮���lastPlayTime�Ϳ�playNextBmp����
		 */
		lastPlayTime = 0;
		scale = 0;
	}

	public Spirit(Bitmap[] bmps) {
		this.bmps = bmps;
		// Ĭ��ʹ��0��Ϊ��һ��
		currentBmpIndex = 0;
		this.bitmap = this.bmps[currentBmpIndex];
		// ��ȡͼƬ�Ŀ�͸�
		this.width = this.bitmap.getWidth();
		this.height = this.bitmap.getHeight();
		/*
		 * lastPlayTimeĬ�Ͼ���0����һ�����е�playNextBmp��ʱ��
		 * �Ǹ�ʱ���ж���Ȼ�ܹ�����֮���lastPlayTime�Ϳ�playNextBmp����
		 */
		lastPlayTime = 0;
		scale = 0;
	}

	public Spirit(Bitmap bitmap) {
		this.bitmap = bitmap;
		// ��ȡͼƬ�Ŀ�͸�
		this.width = this.bitmap.getWidth();
		this.height = this.bitmap.getHeight();
		/*
		 * lastPlayTimeĬ�Ͼ���0����һ�����е�playNextBmp��ʱ��
		 * �Ǹ�ʱ���ж���Ȼ�ܹ�����֮���lastPlayTime�Ϳ�playNextBmp����
		 */
		lastPlayTime = 0;
		scale = 0;
	}

	/**
	 * �����ǰʱ���ͼƬ��һ����ʾʱ�����>= interval��������һ֡��bmp
	 * 
	 * @param interval
	 *            ���ʱ��
	 */
	public void nextBmp(long interval) {
		if (bmps != null && bmps.length > 0) {
			long currentTime = System.currentTimeMillis();
			if (currentTime - lastPlayTime >= interval) {
				currentBmpIndex = (currentBmpIndex + 1) % bmps.length;
				bitmap = bmps[currentBmpIndex];
				lastPlayTime = currentTime;
			}
		}
	}

	/**
	 * �Ȼ����Լ�֮���ٰ�ͼƬ��������һ��
	 * 
	 * @param canvas
	 *            ����
	 */
	public void drawSelf(Canvas canvas) {
		if(scale == 0){
			canvas.drawBitmap(bitmap, x, y, null);
		}else{
			Log.d("INFO", "into drawSelf scale="+scale);
			canvas.drawBitmap(bitmap,null, new RectF(x, y, x+getWidth()*scale, y+getHeight()*scale), null);
		}
		nextBmp(PALY_INTERVAL);
	}

	/**
	 * ��¡�Լ�
	 * @return
	 */
	public Spirit getCloneSpirit(){
		try {
			return (Spirit)this.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
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

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public int getCurrentBmpIndex() {
		return currentBmpIndex;
	}

	public void setCurrentBmpIndex(int currentBmpIndex) {
		this.currentBmpIndex = currentBmpIndex;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
		this.width = this.bitmap.getWidth();
		this.height = this.bitmap.getHeight();
	}

	public Bitmap[] getBmps() {
		return bmps;
	}

	public void setBmps(Bitmap[] bmps) {
		this.bmps = bmps;
	}

	public long getLastPlayTime() {
		return lastPlayTime;
	}

	public void setLastPlayTime(long lastPlayTime) {
		this.lastPlayTime = lastPlayTime;
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

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public Rect getSpiritRect() {
		// TODO Auto-generated method stub
		return new Rect((int)x, (int)y, (int)(x+getWidth()), (int)(y+getHeight()));
	}

	
}
