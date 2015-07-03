/**
 * Project Name:lsn8_customview
 * File Name:VolumeView.java
 * Package Name:com.zht.customview.widget
 * Date:2015-7-3下午4:58:30
 * Copyright (c) 2015, shixi_hongtao@staff.sina.com.cn All Rights Reserved.
 *
*/

package com.zht.customview.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.View;

import com.zht.customview.R;

/**
 * ClassName:VolumeView <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-7-3 下午4:58:30 <br/>
 * @author   acer
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class VolumeView extends View{

	private Bitmap grayImage;
	private Bitmap greenImage;
	public AudioManager am;
	private int streamMaxVolume;
	private int streamVolume;

	

	public int getStreamVolume() {
		return streamVolume;
	}
	public void setStreamVolume(int streamVolume) {
		this.streamVolume = streamVolume;
	}
	public VolumeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		grayImage = BitmapFactory.decodeResource(getResources(), R.drawable.gray);
		greenImage = BitmapFactory.decodeResource(getResources(), R.drawable.green);
		am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		streamMaxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		streamVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC); 
	}
     //测量自己
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
		//判断模式 来决定其应有的尺寸
		//如果是wrap_content
		int viewWidth = grayImage.getWidth() + this.getPaddingLeft() + this.getPaddingRight();
		int viewHeight = (2 * streamMaxVolume - 1) * grayImage.getHeight() + this.getPaddingTop() + this.getPaddingBottom();
		//固定值：包括match_parent 和 100dp这样的固定大小
		if(modeWidth == MeasureSpec.EXACTLY){
			viewWidth = sizeWidth;
		}
		if(modeHeight == MeasureSpec.EXACTLY){
			viewHeight = sizeHeight;
		}
		//自己来设置宽高
		this.setMeasuredDimension(viewWidth, viewHeight);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		int left =  this.getPaddingLeft();
		int top = this.getPaddingTop();
		//画灰色图
		for (int i = 0; i < streamMaxVolume - streamVolume; i++) {
			top = i * 2 * grayImage.getHeight() +this.getPaddingTop();
			canvas.drawBitmap(grayImage, left, top, null);
		}
		//画绿色图
		for (int i = streamMaxVolume - streamVolume; i < streamMaxVolume; i++) {
			top = i * 2 * greenImage.getHeight() +this.getPaddingTop();
			canvas.drawBitmap(greenImage, left, top, null);
		}
		super.onDraw(canvas);
	}
}
