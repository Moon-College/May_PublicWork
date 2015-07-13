package com.tz.flappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

public class Spirit implements Cloneable{

	private final int SPAN_TIME = 50;
	private float x,y; //精灵xy坐标
	private Bitmap bitmap;
	private Bitmap [] bms;  //精灵图片
	private int width,height; //精灵宽高
	private float xSpeed,ySpeed; //精灵速度
	
	private int index;  //当前图片的索引
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
	
	public Spirit(Bitmap bitmap) {
		this.bitmap = bitmap;
		this.width = bitmap.getWidth();
		this.height = bitmap.getHeight();
	}
	
	public Spirit(Bitmap [] bms) {
		// TODO Auto-generated constructor stub
		this.bitmap = bms[0];
		this.bms = bms;
		this.width = bitmap.getWidth();
		this.height = bitmap.getHeight();
	}
	
	/**
	 * 每隔一段时间切换图片
	 * @param span
	 */
	public void nextBitmap(long span){
		if (bms == null || bms.length == 0) {
			return;
		}
		bitmap = bms[index];
		
		if (System.currentTimeMillis() - lastTime >= span) {
			index ++;
			if (index >= bms.length) {
				index = 0;
			}
			lastTime = System.currentTimeMillis();
		}
	}
	
	public void drawSelf(Canvas canvas){
		nextBitmap(SPAN_TIME);
		canvas.drawBitmap(bitmap, x, y, null);
	}
	
	/**
	 * 克隆自己
	 * @return
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
	
	/**
	 * 获取精灵的矩形
	 * @return
	 */
	public RectF getSpiritRect(){
		return new RectF(this.getX(), this.getY(), this.getX()+this.getWidth(), this.getY()+this.getHeight());
	}
}
