package com.knight.custom.view;

import com.knight.custom.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.View;

public class SoundView extends View {

	public AudioManager audioManager;
	private int maxVolume;
	private int currentVolume;
	private Bitmap gray_bitmap , green_bitmap;

	public SoundView(Context context, AttributeSet attrs) {
		super(context, attrs);
		//初始化
		audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		//最大值 当前值
		maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		gray_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gray);
		green_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.green);
	}
	
	/**
	 * 测量宽高
	 * 控件在XML中有三种模式宽高模式:fill_parent/match_parent 、 200dp 、 wrap_content
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//得到控件大小和模式
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
		
		//控件在 wrap_content 模式下的宽高
		int measureWidth = gray_bitmap.getWidth() + this.getPaddingLeft() + this.getPaddingRight();
		int measureHeight = (2 * maxVolume -1) * gray_bitmap.getHeight() + this.getPaddingBottom() + this.getPaddingTop();
		
		//控件在fill_parent/200dp模式下
		if (modeWidth == MeasureSpec.EXACTLY) {
			measureWidth = sizeWidth;
		}
		if (modeHeight == MeasureSpec.EXACTLY) {
			measureHeight = sizeHeight;
		}
		//手动设置宽高
		this.setMeasuredDimension(measureWidth, measureHeight);
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	public int getCurrentVolume() {
		return currentVolume;
	}

	public void setCurrentVolume(int currentVolume) {
		this.currentVolume = currentVolume;
	}

	/**
	 * 绘制图形
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		//确定图形绘制的开始位置
		int top = this.getPaddingTop();
		int left = this.getPaddingLeft();
		//循环绘制灰色
		for (int i = 0; i < maxVolume - currentVolume; i++) {
			top = i * 2 * gray_bitmap.getHeight() + this.getPaddingTop();
			canvas.drawBitmap(gray_bitmap, left, top, null);
		}
		//绘制亮色
		for (int i = maxVolume - currentVolume; i < maxVolume; i++) {
			top = i * 2 * green_bitmap.getHeight() + this.getPaddingTop();
			canvas.drawBitmap(green_bitmap, left, top, null);
		}
		super.onDraw(canvas);
	}
}
