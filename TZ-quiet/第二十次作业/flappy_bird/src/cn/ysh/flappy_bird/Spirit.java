package cn.ysh.flappy_bird;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * 精灵类，用来描述画面上的组件，图形
 * @author Administrator
 *
 */
public class Spirit implements Cloneable{
	//宽高
	private int width;
	private int height;
	//坐标点
	private int x;
	private int y;
	//图片，因为有可能是一张，或者多张，这里给一个和数组两种
	private Bitmap[] bitmaps;
	private Bitmap bitmap;

	//涉及到移动，这里给到速度，小鸟是y方向的速度，管子是x方向的速度
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
	 * 克隆自己
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
