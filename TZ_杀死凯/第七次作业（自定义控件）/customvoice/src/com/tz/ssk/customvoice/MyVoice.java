package com.tz.ssk.customvoice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MyVoice extends View{

	public Bitmap bitmap_off,bitmap_on;
	public AudioManager mAudioManager;
	public int maxvalue,currentvalue;
	private int measuredWidth,measuredHeight;
	public MyVoice(Context context) {
		super(context);
	}
	public MyVoice(Context context, AttributeSet attrs) {
		super(context, attrs);
		bitmap_off=BitmapFactory.decodeResource(getResources(), R.drawable.volume_off);
		bitmap_on=BitmapFactory.decodeResource(getResources(), R.drawable.volume_on);
		mAudioManager=(AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
//		maxvalue=mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
//		currentvalue=mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		maxvalue=mAudioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
		currentvalue=mAudioManager.getStreamVolume(AudioManager.STREAM_RING);
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int mwidth=MeasureSpec.getMode(widthMeasureSpec);
		int mheight=MeasureSpec.getMode(heightMeasureSpec);
		int swidth=MeasureSpec.getSize(widthMeasureSpec);
		int sheight=MeasureSpec.getSize(heightMeasureSpec);
		
		measuredWidth=bitmap_off.getWidth() + this.getPaddingLeft() + this.getPaddingRight();
		measuredHeight=(2 * maxvalue - 1) * bitmap_off.getHeight() + this.getPaddingTop() + this.getPaddingBottom();

		if (mwidth == MeasureSpec.EXACTLY) {
			measuredWidth = swidth;
		} 
		if (mheight == MeasureSpec.EXACTLY) {
			measuredHeight = sheight;
		} 
		this.setMeasuredDimension(measuredWidth,measuredHeight);
		//super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		int left =  this.getPaddingLeft();
		int top = this.getPaddingTop();
		
		for (int i=0; i<maxvalue-currentvalue; i++){
			top = i * 2 * bitmap_off.getHeight() + this.getPaddingTop();
			bitmap_off=Bitmap.createScaledBitmap(bitmap_off, measuredWidth,bitmap_off.getHeight(), true);
			canvas.drawBitmap(bitmap_off,left , top, null);
		}
		for (int i=maxvalue-currentvalue; i<maxvalue; i++){
			top = i * 2 * bitmap_off.getHeight() + this.getPaddingTop();
			bitmap_on=Bitmap.createScaledBitmap(bitmap_on, measuredWidth,bitmap_on.getHeight(), true);
			canvas.drawBitmap(bitmap_on,left , top, null);
		}
		super.onDraw(canvas);
	}
	
}
