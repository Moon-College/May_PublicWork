package com.tz.customsoundview.view;

import com.tz.customsoundview.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CustomSoundView extends View {
	private Bitmap bitmapGreen;
	private Bitmap bitmapGray;
	private AudioManager audioManager;

	// 保存图片绘制的起始和终止X，Y坐标
	private int startDrawX, startDrawY;
	private int endDrawX, endDrawY;

	private int maxVolume;
	private int currVolume;

	public CustomSoundView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		bitmapGray = BitmapFactory.decodeResource(getResources(),
				R.drawable.gray);
		bitmapGreen = BitmapFactory.decodeResource(getResources(),
				R.drawable.green);

		audioManager = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);
		maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		currVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
	}

	/**
	 * Touch事件处理
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float downX = event.getX();
		float downY = event.getY();
		if (event.getAction() == MotionEvent.ACTION_DOWN) {

			// 判断焦点的坐标，左右10个px
			if (downX >= startDrawX - 10 && downX <= endDrawX + 10
					&& downY >= startDrawY && downY <= endDrawY) {
				// 计算落在第几个上
				float index = (downY - getPaddingTop())
						/ (bitmapGray.getHeight() * 2);
				currVolume = (int) (maxVolume - Math.ceil(index));
				audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
						currVolume, AudioManager.FLAG_PLAY_SOUND
								| AudioManager.FLAG_SHOW_UI);
				invalidate();
				return false;
			}
		}
		return super.onTouchEvent(event);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		int left = 0;
		int top = 0;

		for (int i = 0; i < maxVolume - currVolume; i++) {
			top = i * 2 * bitmapGray.getHeight() + this.getPaddingTop();
			canvas.drawBitmap(bitmapGray, left + this.getPaddingLeft(), top,
					null);
		}

		for (int i = maxVolume - currVolume; i < maxVolume; i++) {
			top = i * 2 * bitmapGreen.getHeight() + getPaddingTop();
			canvas.drawBitmap(bitmapGreen, left + this.getPaddingLeft(), top,
					null);
		}
		super.onDraw(canvas);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		// 获取宽和高的大小和模式
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		// 如果是wrap_content模式则使用以下大小
		int measuredWidth = bitmapGray.getWidth() + this.getPaddingLeft()
				+ this.getPaddingRight();
		int measuredHeight = (this.maxVolume * 2 - 1) * bitmapGray.getHeight()
				+ this.getPaddingTop() + this.getPaddingBottom();

		// 计算绘制的（x,y）
		this.startDrawX = this.getPaddingLeft();
		this.startDrawY = this.getPaddingTop();
		this.endDrawX = this.startDrawX + bitmapGray.getWidth();
		this.endDrawY = measuredHeight - this.getPaddingBottom();

		// match_parent或者固定值
		if (widthMode == MeasureSpec.EXACTLY) {
			measuredWidth = widthSize;
		}
		if (heightMode == MeasureSpec.EXACTLY) {
			measuredHeight = heightSize;
		}

		setMeasuredDimension(measuredWidth, measuredHeight);
	}

	public AudioManager getAudioManager() {
		return audioManager;
	}

}
