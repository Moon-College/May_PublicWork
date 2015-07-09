package com.tz.michael.spirit;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * 基类
 * @author admin
 *
 */
public class BaseSpirit {

	/**图片的坐标*/
	private float x;
	private float y;
	/**图片的宽高*/
	private int width;
	private int height;
	/**x和y方向的速度*/
	private float speedX;
	private float speedY;
	/**当前的图片*/
	private Bitmap curBitmap;
	/**精灵的各种图片集合*/
	private Bitmap[] bitmaps;
	/**当前图的下标*/
	private int index;
	/**上次图片改变是的时间毫秒值*/
	private long curTimeMillis;
	
	public BaseSpirit(Bitmap bitmap) {
		this.curBitmap=bitmap;
		this.width=curBitmap.getWidth();
		this.height=curBitmap.getHeight();
	}
	
	public BaseSpirit(Bitmap[] bitmaps) {
		this.bitmaps=bitmaps;
		this.curBitmap=bitmaps[index];
		this.width=curBitmap.getWidth();
		this.height=curBitmap.getHeight();
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

	/**
	 * 切换下一个图片
	 * @param seconds
	 */
	public void next(int seconds){
		if(bitmaps==null||bitmaps.length==0){
			return ;
		}
		curBitmap=bitmaps[index];
		long variableTimeMillis=System.currentTimeMillis();
		if(variableTimeMillis-curTimeMillis>=seconds){
			index=++index%bitmaps.length;
		}
		curTimeMillis=System.currentTimeMillis();
	}
	
	/**
	 * 绘制自己
	 * @param canvas
	 */
	public void drawSelf(Canvas canvas){
		next(100);
		canvas.drawBitmap(curBitmap, x, y, null);
	}
	
}
