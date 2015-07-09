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
     private Bitmap bitmap_gray ; //��������ͼƬ
     private Bitmap bitmap_green; //��ǰ��������ɫͼƬ
     public AudioManager am ;  //����ϵͳ�ķ���
     private  int maxVolume ; //��������ֵ
     public  int currentVolume; //��ǰ������ֵ
	public int getCurrentVolume() {
		return currentVolume;
	}

	public void setCurrentVolume(int currentVolume) {
		this.currentVolume = currentVolume;
	}

	//�����Ϊ�������� ����   AttributeSet ��������xml�ļ�
	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
       bitmap_gray  = BitmapFactory.decodeResource(getResources(), R.drawable.gray);
       bitmap_green = BitmapFactory.decodeResource(getResources(), R.drawable.green);
       am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE); //��ʼ��
        //��ȡϵͳ������ֵ 
       maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
       currentVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);
       Log.i("INFO", "MAX VOLUME" + maxVolume);
       
	}
	
    //ͨ��������ʵ�ֵĻ����ķ���
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
       int widthSize =MeasureSpec.getSize(widthMeasureSpec);
       int widthMode = MeasureSpec.getMode(widthMeasureSpec);
       int heightSize = MeasureSpec.getSize(heightMeasureSpec);
       int heightMode = MeasureSpec.getMode(heightMeasureSpec);
       
       int meaSureWidth = bitmap_gray.getWidth() + this.getPaddingRight() + this.getPaddingLeft();
       int meaSureHeight = (2 *maxVolume -1)*bitmap_gray.getHeight() +this.getPaddingTop() + this.getPaddingBottom();
       
       //�ж��Ƿ�Ϊȷ��ֵ
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
      //��������ͼƬ
      for (int i = 0; i < maxVolume  - currentVolume; i++) {
	    top = i*2*bitmap_gray.getWidth() + this.getPaddingRight() + this.getPaddingLeft();
	    //Ȼ��ͨ������������ 
	    canvas.drawBitmap(bitmap_gray, left, top, null);
 	  }
     for (int i = maxVolume - currentVolume ; i < maxVolume; i++) {
	     top = i*2*bitmap_gray.getWidth();
	     canvas.drawBitmap(bitmap_green, left, top, null);
	 }
    
		super.onDraw(canvas);
	}
	
	

}
