package com.rocy.tzclass19_bird;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * 精灵类
 * @author Rocy
 *
 */
public class Spirit implements Cloneable{
	
    private int x;//宽
    private int y;//高
    private float speed;//速率
    private Bitmap bitmap ;//当前得到的图片
    private Bitmap [] bitmaps; //图片组
    private int bitmapIndex ;//图标索引
    private Rect rect;
    
    public Spirit(int x ,int y ,Bitmap bitmap) {
		this.x = x;
		this.y = y;
		this.bitmap = bitmap;
	}
    
    public Spirit(int x ,int y ,Bitmap bitmaps[]) {
  		this.x = x;
  		this.y = y;
  		this.bitmaps = bitmaps;
  	}
    
    public Spirit(int x ,int y ){
    	this.x = x;
  		this.y = y;
    }
	
	public Spirit() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the speed
	 */
	public float getSpeed() {
		return speed;
	}
	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	/**
	 * @return the bitmap
	 */
	public Bitmap getBitmap() {
		return bitmap;
	}
	/**
	 * @param bitmap the bitmap to set
	 */
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	/**
	 * @return the bitmaps
	 */
	public Bitmap[] getBitmaps() {
		return bitmaps;
	}
	/**
	 * @param bitmaps the bitmaps to set
	 */
	public void setBitmaps(Bitmap[] bitmaps) {
		this.bitmaps = bitmaps;
	}
	
	
	/**
	 * 绘制精灵
	 * @param canvas
	 * @param x
	 * @param y
	 */
	public void drawAnimationSpirit(Canvas canvas){
		//当bitmao数组有货的时候才开始绘制
		if(bitmap != null){
			rect = new Rect(x, y, x+bitmap.getWidth(), y+bitmap.getHeight());
			canvas.drawBitmap(bitmap, null, rect, null);
		}
		
		
	}
	
	public void drawAnimationSpirit2(Canvas canvas){
		if(bitmaps.length >0){
			if(bitmapIndex >= bitmaps.length){
				bitmapIndex = 0;
			}
			rect = new Rect(x, y, x+bitmaps[bitmapIndex].getWidth(), y+bitmaps[bitmapIndex].getHeight());
			Bitmap bitmap2 = bitmaps[bitmapIndex];
			canvas.drawBitmap(bitmap2, null, rect, null);
			bitmapIndex ++;
		}
	}
	

    
	public Spirit getClone(){
		Spirit spirit;
		 try {
			 spirit =(Spirit) this.clone();
		} catch (CloneNotSupportedException e) {
			//如果克隆不成 那只能new 出来了； 
			return new Spirit();
		}
		return spirit;
		
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the rect
	 */
	public Rect getRect() {
		return rect;
	}

	/**
	 * @param rect the rect to set
	 */
	public void setRect(Rect rect) {
		this.rect = rect;
	}
	
	
}
