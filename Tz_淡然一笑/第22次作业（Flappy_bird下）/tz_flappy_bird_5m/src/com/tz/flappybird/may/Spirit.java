package com.tz.flappybird.may;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * 精灵类
 * @author fcc
 *
 */
public class Spirit implements Cloneable {
	
	private float x; //精灵的x坐标
	private float y; //精灵的y坐标
	
	private Bitmap bitmap;  
	private Bitmap [] bitmaps; //精灵的图片

	private int width;	//精灵的宽
	private int height; //精灵的宽
	
	private float xSpeed;
	private float ySpeed;//精灵的速度
	
	private long lastTime;  //上一次时间
	private int index;
	
	private final int SPAN_TIME = 50;
	
	public Spirit(Bitmap bitmap){
		this.bitmap = bitmap;
		this.width = bitmap.getWidth();
		this.height = bitmap.getHeight();
	}
	
	public Spirit(Bitmap [] bitmaps){
		this.bitmap = bitmaps[0];  //默认为第一张图片
		this.bitmaps = bitmaps;
		this.width = bitmap.getWidth();
		this.height = bitmap.getHeight();
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
		this.width = bitmap.getWidth();	//裁剪后的图片的宽度
		this.height = bitmap.getHeight(); //裁剪后的图片的高度
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

	/**
	 * 每隔一段时间切换图片
	 * @param span 时间
	 */
	public void nextBitmap(long span){
		if(bitmaps == null || bitmaps.length == 0){
			return;
		}
		bitmap = bitmaps[index];
		if(System.currentTimeMillis() - lastTime >= span){
			index++;
			if(index >= bitmaps.length){
				index = 0;
			}
			lastTime = System.currentTimeMillis();
		}
	}
	
	/**
	 * 绘制自己
	 * @param canvas 画布
	 */
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
