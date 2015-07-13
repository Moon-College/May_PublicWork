package com.decent.decentgame.bean;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

public class Spirit implements Cloneable {
	/**
	 * 播放的时间间隔，单位ms
	 */
	public static final int PALY_INTERVAL = 50;
	/**
	 * 上次播放的时间
	 */
	private long lastPlayTime;
	/**
	 * x坐标
	 */
	private float x;
	/**
	 * y坐标
	 */
	private float y;
	/**
	 * 图片宽度
	 */
	private float width;
	/**
	 * 图片高度
	 */
	private float height;
	/**
	 * 当前播放的图片的index
	 */
	private int currentBmpIndex;
	/**
	 * 当前播放的图片
	 */
	private Bitmap bitmap;
	/**
	 * 需要播放的图片的列表
	 */
	private Bitmap[] bmps;

	/**
	 * x方向上的速度
	 */
	private float xSpeed;
	/**
	 * y方向上的速度
	 */
	private float ySpeed;

	/**
	 * 缩放图片比例
	 */
	private float scale;
	/**
	 * 构造函数
	 * 
	 * @param x
	 *            图片的x坐标
	 * @param y
	 *            图片的y坐标
	 * @param bmps
	 *            要播放的图片列表，bmps的第一张就是要播放的第一张
	 */
	public Spirit(float x, float y, Bitmap[] bmps) {
		this.x = x;
		this.y = y;
		this.bmps = bmps;
		// 默认使用0的为第一张
		currentBmpIndex = 0;
		this.bitmap = this.bmps[currentBmpIndex];
		// 获取图片的宽和高
		this.width = this.bitmap.getWidth();
		this.height = this.bitmap.getHeight();
		/*
		 * lastPlayTime默认就是0，第一次运行到playNextBmp的时候，
		 * 那个时间判断自然能够过，之后的lastPlayTime就靠playNextBmp更新
		 */
		lastPlayTime = 0;
		scale = 0;
	}

	public Spirit(Bitmap[] bmps) {
		this.bmps = bmps;
		// 默认使用0的为第一张
		currentBmpIndex = 0;
		this.bitmap = this.bmps[currentBmpIndex];
		// 获取图片的宽和高
		this.width = this.bitmap.getWidth();
		this.height = this.bitmap.getHeight();
		/*
		 * lastPlayTime默认就是0，第一次运行到playNextBmp的时候，
		 * 那个时间判断自然能够过，之后的lastPlayTime就靠playNextBmp更新
		 */
		lastPlayTime = 0;
		scale = 0;
	}

	public Spirit(Bitmap bitmap) {
		this.bitmap = bitmap;
		// 获取图片的宽和高
		this.width = this.bitmap.getWidth();
		this.height = this.bitmap.getHeight();
		/*
		 * lastPlayTime默认就是0，第一次运行到playNextBmp的时候，
		 * 那个时间判断自然能够过，之后的lastPlayTime就靠playNextBmp更新
		 */
		lastPlayTime = 0;
		scale = 0;
	}

	/**
	 * 如果当前时间和图片上一次显示时间相差>= interval，换到下一帧的bmp
	 * 
	 * @param interval
	 *            间隔时间
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
	 * 先绘制自己之后，再把图片调整到下一张
	 * 
	 * @param canvas
	 *            画布
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
	 * 克隆自己
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
