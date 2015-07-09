package com.tz.flappybird.may;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * ������
 * @author fcc
 *
 */
public class Spirit implements Cloneable {
	
	private float x; //�����x����
	private float y; //�����y����
	
	private Bitmap bitmap;  
	private Bitmap [] bitmaps; //�����ͼƬ

	private int width;	//����Ŀ�
	private int height; //����Ŀ�
	
	private float xSpeed;
	private float ySpeed;//������ٶ�
	
	private long lastTime;  //��һ��ʱ��
	private int index;
	
	private final int SPAN_TIME = 50;
	
	public Spirit(Bitmap bitmap){
		this.bitmap = bitmap;
		this.width = bitmap.getWidth();
		this.height = bitmap.getHeight();
	}
	
	public Spirit(Bitmap [] bitmaps){
		this.bitmap = bitmaps[0];  //Ĭ��Ϊ��һ��ͼƬ
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
		this.width = bitmap.getWidth();	//�ü����ͼƬ�Ŀ��
		this.height = bitmap.getHeight(); //�ü����ͼƬ�ĸ߶�
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
	 * ÿ��һ��ʱ���л�ͼƬ
	 * @param span ʱ��
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
	 * �����Լ�
	 * @param canvas ����
	 */
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
