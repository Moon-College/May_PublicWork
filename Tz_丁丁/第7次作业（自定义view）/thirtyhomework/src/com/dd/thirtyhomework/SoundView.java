package com.dd.thirtyhomework;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class SoundView extends View {

	private Bitmap bitmap_gray;
	private Bitmap bitmap_green;
	public AudioManager am;
	private int maxVolume;
	private int currentVolume;
	private int width;
	private int height;
	private float afterWidth;
	private float afterHeight;
	private int i=0,j=0;
	public SoundView(Context context, AttributeSet attrs) {
		super(context, attrs);
		i=0;
		Log.v("home", "构造器");
		bitmap_gray = BitmapFactory.decodeResource(getResources(), R.drawable.gray);
		bitmap_green = BitmapFactory.decodeResource(getResources(), R.drawable.green);
		am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		currentVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);
		width = bitmap_gray.getWidth();
		height = bitmap_gray.getHeight();
	}
	public int getCurrentVolume() {
		return currentVolume;
	}
	public void setCurrentVolume(int currentVolume) {
		this.currentVolume = currentVolume;
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		List<Integer> integers=new ArrayList<Integer>();
		List<Integer> integers1=new ArrayList<Integer>();
		int measuredWidth = 0;
		int measuredHeight = 0;
		int sizeWidth=0;
		int sizeHeight=0;
		Log.v("home", "进来");
			sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
			int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
			sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
			int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
		measuredWidth = width + this.getPaddingLeft() + this.getPaddingRight();
		measuredHeight = (2 * maxVolume - 1) * height + this.getPaddingTop() + this.getPaddingBottom();
		if (modeWidth == MeasureSpec.EXACTLY){
			measuredWidth = sizeWidth;
			i=1;
			Log.v("home", "modeWidth"+"EXACTLY");
		}
		if (modeWidth == MeasureSpec.AT_MOST){
			i=2;
			Log.v("home", "modeWidth"+"AT_MOST");
		}
		if (modeWidth == MeasureSpec.UNSPECIFIED){
			i=3;
			Log.v("home", "modeWidth"+"UNSPECIFIED");
		}
		if (modeHeight == MeasureSpec.EXACTLY){
			measuredHeight = sizeHeight;
			Log.v("home", "modeHeight"+"EXACTLY");
			j=1;
		}
		if (modeHeight ==MeasureSpec.AT_MOST) {
			j=2;
			Log.v("home", "modeHeight"+"AT_MOST");
		}
		if (modeHeight ==MeasureSpec.UNSPECIFIED) {
			j=3;
			Log.v("home", "modeHeight"+"UNSPECIFIED");
		}
		/*
		 * 算图片具体平均高度宽度
		 */
//		Log.v("home", "1measuredHeight"+measuredHeight);
		afterWidth = measuredWidth - this.getPaddingLeft() - this.getPaddingRight();
		afterHeight = (measuredHeight - this.getPaddingTop() - this.getPaddingBottom())/(2 * maxVolume - 1);
		if (j==2) {
		}
		if (i==j) {
		}else {
//			Log.v("home", "重新计算了");
			if (sizeHeight!=sizeWidth) {
				integers.clear();
			}
			measuredHeight = (int) (afterHeight*(2 * maxVolume - 1) +this.getPaddingTop() + this.getPaddingBottom());
		}
		if (i==1&&j==2) {
		}
		Log.v("home", "measuredHeight"+sizeHeight+"------measuredWidth"+sizeWidth);
		
		integers.add(measuredWidth);
		integers1.add(measuredHeight);
		
		setMeasuredDimension(integers.get(0), integers1.get(0));
		
	}
	@Override
	protected void onDraw(Canvas canvas) {
		Log.v("home", "i="+i+"j="+j);
		Log.v("home", "onDraw");
		Matrix matrix = new Matrix();
		matrix.postScale((float)(afterWidth/width), (float)(afterHeight/height));
		
		Bitmap bitmapGray = Bitmap.createBitmap(bitmap_gray, 0, 0, width,height, matrix, true);
		
		Bitmap bitmapGreen = Bitmap.createBitmap(bitmap_green, 0, 0, width,height, matrix, true);
		int left = this.getPaddingLeft();
		int top = this.getPaddingTop();
		Log.v("home", "onDraw"+bitmapGray.getHeight()+"---"+height);
		for (int i = 0; i < maxVolume - currentVolume; i++) {
			top = i * 2 * bitmapGray.getHeight() + this.getPaddingTop();
			canvas.drawBitmap(bitmapGray, left, top, null);
		}
		for (int i = maxVolume - currentVolume; i < maxVolume; i++) {
			top = i * 2 * bitmapGreen.getHeight() + this.getPaddingTop();
			canvas.drawBitmap(bitmapGreen, left, top, null);
		}
		super.onDraw(canvas);
	}
}
