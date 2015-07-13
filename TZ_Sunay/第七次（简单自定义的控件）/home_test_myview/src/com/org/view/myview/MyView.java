package com.org.view.myview;

import com.example.home_test_myview.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MyView  extends View{
     private Bitmap bitmap_gray ; //背景音量图片
     private Bitmap bitmap_green; //当前的音量绿色图片
     public AudioManager am ;  //调用系统的服务
     private  int maxVolume ; //最大的音量值
     public  int currentVolume; //当前的音量值
	public int getCurrentVolume() {
		return currentVolume;
	}

	public void setCurrentVolume(int currentVolume) {
		this.currentVolume = currentVolume;
	}

	//这里分为三个构造 寒素   AttributeSet 用来加载xml文件
	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
       bitmap_gray  = BitmapFactory.decodeResource(getResources(), R.drawable.gray);
       bitmap_green = BitmapFactory.decodeResource(getResources(), R.drawable.green);
       am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE); //初始化
        //获取系统的音量值 
       maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
       currentVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);
       Log.i("INFO", "MAX VOLUME" + maxVolume);
       
	}
	
    //通过画布来实现的画布的方法
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
       int widthSize =MeasureSpec.getSize(widthMeasureSpec);
       int widthMode = MeasureSpec.getMode(widthMeasureSpec);
       int heightSize = MeasureSpec.getSize(heightMeasureSpec);
       int heightMode = MeasureSpec.getMode(heightMeasureSpec);
       
       int meaSureWidth = bitmap_gray.getWidth() + this.getPaddingRight() + this.getPaddingLeft();
       int meaSureHeight = (2 *maxVolume -1)*bitmap_gray.getHeight() +this.getPaddingTop() + this.getPaddingBottom();
       
       //判断是否为确定值
       if(widthMode == MeasureSpec.EXACTLY){
    	   meaSureWidth = widthSize;
       }
       if(heightMode == MeasureSpec.EXACTLY){
    	    meaSureHeight = heightSize;
       }
       this.setMeasuredDimension(meaSureWidth, meaSureHeight);
       
		//super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	@Override
	protected void onDraw(Canvas canvas) {
      int top  = this.getPaddingTop();
      int left = this.getPaddingLeft();
      //画出背景图片
      for (int i = 0; i < maxVolume  - currentVolume; i++) {
	    top = i*2*bitmap_gray.getWidth() + this.getPaddingRight() + this.getPaddingLeft();
	    //然后通过画布画出来 
	    canvas.drawBitmap(bitmap_gray, left, top, null);
 	  }
     for (int i = maxVolume - currentVolume ; i < maxVolume; i++) {
	     top = i*2*bitmap_gray.getWidth();
	     canvas.drawBitmap(bitmap_green, left, top, null);
	 }
    
		super.onDraw(canvas);
	}
	
	

}
