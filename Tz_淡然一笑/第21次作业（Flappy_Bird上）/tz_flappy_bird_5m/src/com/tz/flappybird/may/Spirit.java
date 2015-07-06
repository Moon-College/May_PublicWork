package com.tz.flappybird.may;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * ������
 * @author fcc
 *
 */
public class Spirit {
	
	private float x; //�����x����
	private float y; //�����y����
	
	private Bitmap bitmap;  
	private Bitmap [] bitmaps; //�����ͼƬ

	private int width;	//����Ŀ�
	private int height; //����Ŀ�
	
	private float speed_x;
	private float speed_y; //������ٶ�
	
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
	
	
}
