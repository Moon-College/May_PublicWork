package com.zjm.custom.view;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.AudioManager;
import android.text.BoringLayout;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;

import com.zjm.custom.R;

/**
 * onMeasure 进行测量
 * onLayout 进行位置摆放
 * onDraw 进行绘制
 * */
public class SoundView extends View {
	
	public final static String TAG = SoundView.class.getSimpleName().toString();

	Paint paint;
	private Bitmap gray;
	private Bitmap green;
	public int maxVolumn;
	private int currentVolumn;
	public AudioManager am;
	
	// 音量块宽度
	private int bmpWidth;
	private int bmpHeight;
	private float bmpScalWidth;
	private float bmpScalHeight;
	
	private Layout mLayout;

	private Bitmap newGray;

	private Bitmap newGreen;
	
	public SoundView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
		paint = new Paint();
		gray = BitmapFactory.decodeResource(context.getResources(), R.drawable.gray);
		green = BitmapFactory.decodeResource(context.getResources(), R.drawable.green);
		bmpWidth = gray.getWidth();
		bmpHeight = gray.getHeight();
		am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		maxVolumn = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);//最大媒体音量值
		currentVolumn = am.getStreamVolume(AudioManager.STREAM_MUSIC);//当前媒体音量值
	}
	
	 private void init(Context context) {
		
	}

	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//首先获取父控件指定的宽高和模式
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		
		//测量容器的宽高
		int measuredWidth = bmpWidth + this.getPaddingLeft() +this.getPaddingRight();
		int measuredHeight = bmpHeight*(2*maxVolumn -1) + this.getPaddingTop() + this.getPaddingBottom();
		
		if(widthMode == MeasureSpec.EXACTLY){
			measuredWidth = widthSize;
		}
		
		if(heightMode == MeasureSpec.EXACTLY){
			measuredHeight = heightSize;
		}
		
		// 设置模式后计算音量块平均宽高
		bmpScalWidth = (measuredWidth - this.getPaddingRight()-this.getPaddingLeft());
		bmpScalHeight = (measuredHeight-this.getPaddingTop() - this.getPaddingBottom())/(2*maxVolumn -1);
		
		//计算音量块平均高度后需重新计算容器高度，以免音量块的高度总和小于容器高度
		measuredHeight = (int)Math.floor(bmpScalHeight*(2*maxVolumn -1) + this.getPaddingTop() + this.getPaddingBottom());
		
		this.setMeasuredDimension(measuredWidth, measuredHeight);
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		Matrix matrix = new Matrix();  
		matrix.postScale((float)(bmpScalWidth/bmpWidth), (float)(bmpScalHeight/bmpHeight));
		newGray = Bitmap.createBitmap(gray, 0, 0, bmpWidth, bmpHeight,  
                matrix, true);
		Log.d(TAG,"width:"+newGray.getWidth()+"height:"+newGray.getHeight());
		newGreen = Bitmap.createBitmap(green, 0, 0, bmpWidth, bmpHeight,  
				matrix, true);
		Log.d(TAG,"width:"+newGreen.getWidth()+"height:"+newGreen.getHeight());
		int top = this.getPaddingTop();//绘制图片的Y坐标
		int left = this.getPaddingLeft();//绘制图片的x坐标
		for(int i=0;i<maxVolumn;i++){
			if(i<maxVolumn - currentVolumn){
				//灰色
				top = i*2*newGray.getHeight() +  this.getPaddingTop();
				canvas.drawBitmap(newGray, left, top,null);
			}else{
				//绿色
				top = i*2*newGreen.getHeight() +  this.getPaddingTop();
				canvas.drawBitmap(newGreen, left, top,null);
			}
		}
		
		super.onDraw(canvas);
	}

	public int getCurrentVolumn() {
		return currentVolumn;
	}

	public void setCurrentVolumn(int currentVolumn) {
		this.currentVolumn = currentVolumn;
	}
	
	

}
