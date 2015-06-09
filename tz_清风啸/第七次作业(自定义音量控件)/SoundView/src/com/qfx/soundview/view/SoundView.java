package com.qfx.soundview.view;

import com.qfx.soundview.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.View;

public class SoundView extends View {

	private Bitmap greyBmp;
	private Bitmap greenBmp;
	private AudioManager au;
	//最大音量值
	private int maxVolume;
	//当前音量值
	private int currentVolume;
	
	public SoundView(Context context, AttributeSet attrs) {
		super(context, attrs);
		greenBmp = BitmapFactory.decodeResource(getResources(), R.drawable.green);
		greyBmp = BitmapFactory.decodeResource(getResources(), R.drawable.gray);
		au = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		maxVolume = au.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		currentVolume = au.getStreamVolume(AudioManager.STREAM_MUSIC);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		//获取父控件指定的宽高和模式
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
		//如果是wrap_content
		int measuredWidth = greyBmp.getWidth() + this.getPaddingLeft() + this.getPaddingRight();
		int measuredHeight = (2 * maxVolume - 1)*greenBmp.getHeight() + this.getPaddingTop() + this.getPaddingBottom();
		if (modeWidth == MeasureSpec.EXACTLY) {
			measuredWidth = sizeWidth;
		} 
		if (modeHeight == MeasureSpec.EXACTLY) {
			measuredHeight = sizeHeight;
		}
		this.setMeasuredDimension(measuredWidth, measuredHeight);
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//绘制音量图片
		//加载图片
		int left = this.getPaddingLeft();
		int top = this.getPaddingTop();
		//绘制灰色图片
		for (int i = 0; i < maxVolume - currentVolume; i++) {
			top = i * 2 * greyBmp.getHeight() + this.getPaddingTop();
			canvas.drawBitmap(greyBmp, left, top, null);
		}
		//绘制绿色图片
		for (int i = maxVolume - currentVolume; i < maxVolume; i++) {
			top = i * 2 * greenBmp.getHeight() + this.getPaddingTop();
			canvas.drawBitmap(greenBmp, left, top, null);
		}
	}

	public void adjustDownVolume() {
		au.adjustStreamVolume(AudioManager.STREAM_MUSIC, 
				AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
		this.currentVolume = au.getStreamVolume(AudioManager.STREAM_MUSIC);
		this.invalidate();
	}
	
	public void adjustUpVolume() {
		au.adjustStreamVolume(AudioManager.STREAM_MUSIC, 
				AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
		this.currentVolume = au.getStreamVolume(AudioManager.STREAM_MUSIC);
		this.invalidate();
	}
}
