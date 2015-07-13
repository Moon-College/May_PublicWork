package com.tz.flappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

public class Spirit implements Cloneable{

	private final int SPAN_TIME = 50;
	private float x,y; //����xy����
	private Bitmap bitmap;
	private Bitmap [] bms;  //����ͼƬ
	private int width,height; //������
	private float xSpeed,ySpeed; //�����ٶ�
	
	private int index;  //��ǰͼƬ������
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
	 * ÿ��һ��ʱ���л�ͼƬ
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
	 * ��¡�Լ�
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
	 * ��ȡ����ľ���
	 * @return
	 */
	public RectF getSpiritRect(){
		return new RectF(this.getX(), this.getY(), this.getX()+this.getWidth(), this.getY()+this.getHeight());
	}
}
