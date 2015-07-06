package com.decent.decentgame.bean;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Spirit {
	/**
	 * ���ŵ�ʱ��������λms
	 */
	public static final int PALY_INTERVAL = 500;
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
	 * ���캯��
	 * @param x ͼƬ��x����
	 * @param y ͼƬ��y����
	 * @param bmps Ҫ���ŵ�ͼƬ�б�bmps�ĵ�һ�ž���Ҫ���ŵĵ�һ��
	 */
	public Spirit(float x,float y,Bitmap[] bmps){
		this.x = x;
		this.y = y;
		this.bmps = bmps;
		//Ĭ��ʹ��0��Ϊ��һ��
		currentBmpIndex = 0;
		this.bitmap = this.bmps[currentBmpIndex];
		//��ȡͼƬ�Ŀ�͸�
		this.width = this.bitmap.getWidth();
		this.height = this.bitmap.getHeight();
		/*
		 * lastPlayTimeĬ�Ͼ���0����һ�����е�playNextBmp��ʱ��
		 * �Ǹ�ʱ���ж���Ȼ�ܹ�����֮���lastPlayTime�Ϳ�playNextBmp����
		 */
		lastPlayTime = 0;
	}
	
	/**
	 * �����ǰʱ���ͼƬ��һ����ʾʱ�����>= interval��������һ֡��bmp
	 * @param interval ���ʱ��
	 */
	public void nextBmp(long interval){
		long currentTime = System.currentTimeMillis();
		if(currentTime - lastPlayTime >= interval){
			currentBmpIndex = (currentBmpIndex + 1)%bmps.length;
			bitmap = bmps[currentBmpIndex];
			lastPlayTime = currentTime;
		}
	}
	
	/**
	 * �Ȼ����Լ�֮���ٰ�ͼƬ��������һ��
	 * @param canvas ����
	 */
	public void drawSelf(Canvas canvas){
		canvas.drawBitmap(bitmap, x, y, null);
		nextBmp(PALY_INTERVAL);
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
	
	
}
