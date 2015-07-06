package com.dd.flappy_bird.spirit;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Spirit {
	private final int SPAN_TIME = 50;
	private float x;
	private float y;//精灵的xy坐标
	
	private Bitmap bitmap;
	private Bitmap[] bms;//精灵的图片
	
	private int width;
	private int height;//精灵的高度
	
	private float xSpeed;
	private float ySpeed;//精灵的速度
	
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
	//根据传过来的图片获取宽高
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
	 * 每隔一段时间切换图片
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
	 * 绘制自己
	 */
	public void drawSelf(Canvas canvas){
		nextBitmap(SPAN_TIME);
		canvas.drawBitmap(bitmap, x, y, null);
	}
}
