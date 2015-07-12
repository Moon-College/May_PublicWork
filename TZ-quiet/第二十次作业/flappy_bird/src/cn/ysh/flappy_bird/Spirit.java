package cn.ysh.flappy_bird;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * �����࣬�������������ϵ������ͼ��
 * @author Administrator
 *
 */
public class Spirit implements Cloneable{
	//���
	private int width;
	private int height;
	//�����
	private int x;
	private int y;
	//ͼƬ����Ϊ�п�����һ�ţ����߶��ţ������һ������������
	private Bitmap[] bitmaps;
	private Bitmap bitmap;

	//�漰���ƶ�����������ٶȣ�С����y������ٶȣ�������x������ٶ�
	private int xSpeed;
	private int ySpeed;
	private long lastTime;
	private int index;
	private final int PER_TIME = 100;
	
	public Spirit(Bitmap bitmap){
		this.bitmap = bitmap;
		this.width = bitmap.getWidth();
		this.height = bitmap.getHeight();
	}
	
	public Spirit(Bitmap[] bitmaps){
		this.bitmaps = bitmaps;
		this.bitmap = bitmaps[0];
		this.width = bitmap.getWidth();
		this.height = bitmap.getHeight();
	}
	
	private void nextBitmap(long time){
		if(bitmaps == null || bitmaps.length == 0){
			return;
		}
		bitmap = bitmaps[index];
		if(System.currentTimeMillis() - lastTime >= time){
			index++;
			bitmap = bitmaps[index];
			if(index >= bitmaps.length - 1){
				index = 0;
			}
			lastTime = System.currentTimeMillis();
		}
	}
	
	public void drawSelf(Canvas canvas){
		nextBitmap(PER_TIME);
		canvas.drawBitmap(bitmap, x, y, null);
	}
	
	public Rect getRect(){
		return new Rect(getX(), getY(), getX()+getWidth(), getY()+getHeight());
	}
	
	/**
	 * ��¡�Լ�
	 */
	public Spirit getCloneObject(){
		try {
			return (Spirit) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}

	public int getySpeed() {
		return ySpeed;
	}

	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
		this.height = bitmap.getHeight();
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
}
