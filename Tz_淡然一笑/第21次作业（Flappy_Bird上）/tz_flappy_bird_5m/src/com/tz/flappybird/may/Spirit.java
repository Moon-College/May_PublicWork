package com.tz.flappybird.may;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * 精灵类
 * @author fcc
 *
 */
public class Spirit {
	
	private float x; //精灵的x坐标
	private float y; //精灵的y坐标
	
	private Bitmap bitmap;  
	private Bitmap [] bitmaps; //精灵的图片

	private int width;	//精灵的宽
	private int height; //精灵的宽
	
	private float speed_x;
	private float speed_y; //精灵的速度
	
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
	public float getSpeed_x() {
		return speed_x;
	}
	public void setSpeed_x(float speed_x) {
		this.speed_x = speed_x;
	}
	public float getSpeed_y() {
		return speed_y;
	}
	public void setSpeed_y(float speed_y) {
		this.speed_y = speed_y;
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
	
	
}
