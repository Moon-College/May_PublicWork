package com.casit.hc;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

public class Spirit implements Cloneable{
	private float x;
	private float y;
	private Bitmap bitmap;
	private Bitmap[] bms;
	private int width;//精灵的宽
	private int height;//精灵的宽高
	private float speedX; //精灵的速度
	private float speedY;//精灵Y方向速度
	private int index;
	private long lastTime;
	public Spirit(Bitmap bitmap){
		this.bitmap = bitmap;
		this.width = bitmap.getWidth();
		this.height = bitmap.getHeight();
	}
	public Spirit(Bitmap[] bms){
		this.bitmap = bms[0];
		this.bms = bms;
		this.width = this.bitmap.getWidth();
		this.height = this.bitmap.getHeight();		
	}
	public void nextBitmap(long span){
		if(bms==null||bms.length==0){
			return ;
		}
		bitmap = bms[index];
		if(System.currentTimeMillis()-lastTime>=span){
			index++;
			if(index>=bms.length)
				index = index % bms.length;
		}
		lastTime = System.currentTimeMillis();
	}
	public void drawSelf(Canvas canvas){
		nextBitmap(100);
		canvas.drawBitmap(bitmap, x, y, null);
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
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
		this.width = bitmap.getWidth();
		this.height = bitmap.getHeight();
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
	public float getSpeedX() {
		return speedX;
	}
	public void setSpeedX(float speedX) {
		this.speedX = speedX;
	}
	public float getSpeedY() {
		return speedY;
	}
	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}
	
	public Spirit getCloneObject(){
		try {
			return (Spirit) this.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/*
	 *获取该精灵的举行
	 */
	public RectF getSpiritRect(){
		return new RectF(this.getX(),this.getY(),this.getX()+this.getWidth(),this.getY()+this.getHeight());
	}

}
