package com.dd.flappy_bird.spirit;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Spirit {
	private final int SPAN_TIME = 50;
	private float x;
	private float y;//�����xy����
	
	private Bitmap bitmap;
	private Bitmap[] bms;//�����ͼƬ
	
	private int width;
	private int height;//����ĸ߶�
	
	private float xSpeed;
	private float ySpeed;//������ٶ�
	
	private int index;
	private long lastTime;
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
	}
	public Bitmap[] getBms() {
		return bms;
	}
	public void setBms(Bitmap[] bms) {
		this.bms = bms;
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
	//���ݴ�������ͼƬ��ȡ���
	public Spirit(Bitmap b){
		this.bitmap = b;
		this.width = bitmap.getWidth();
		this.height = bitmap.getHeight();
	}
	
	public Spirit(Bitmap[] bms){
		this.bitmap = bms[0];
		this.bms = bms;
		this.width = bitmap.getWidth();
		this.height = bitmap.getHeight();
	}
	/**
	 * ÿ��һ��ʱ���л�ͼƬ
	 */
	public void nextBitmap(long span){
		if (bms ==null||bms.length == 0) {
			return;
		}
		bitmap = bms[index];
		if (System.currentTimeMillis() - lastTime >= span) {
			index++;
			if (index >= bms.length) {
				index = 0;
			}
			lastTime = System.currentTimeMillis();
		}
	}
	/**
	 * �����Լ�
	 */
	public void drawSelf(Canvas canvas){
		nextBitmap(SPAN_TIME);
		canvas.drawBitmap(bitmap, x, y, null);
	}
}
