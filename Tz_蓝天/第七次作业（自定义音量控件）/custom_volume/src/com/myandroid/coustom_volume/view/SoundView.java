package com.myandroid.coustom_volume.view;

import com.myandroid.coustom_volume.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.View;

public class SoundView extends View {

	private Bitmap bitmap_gray;
	private Bitmap bitmap_green;
	public AudioManager am; // 音频管理器
	private int maxVolume; // 最大音量值
	public int currentVolume; // 当前音量值

	public int getCurrentVolume() {
		return currentVolume;
	}

	public void setCurrentVolume(int currentVolume) {
		this.currentVolume = currentVolume;
	}

	public SoundView(Context context, AttributeSet attrs) {
		super(context, attrs);

		bitmap_gray = BitmapFactory.decodeResource(getResources(),
				R.drawable.gray);
		bitmap_green = BitmapFactory.decodeResource(getResources(),
				R.drawable.green);
		am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

		maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		currentVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// 获取父控件指定的宽高和模式
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
		
		// 模式为wrap_content
		int measuredWidth = bitmap_gray.getWidth() + this.getPaddingLeft()
				+ this.getPaddingRight();
		int measuredHeight = (2 * maxVolume - 1) * bitmap_gray.getHeight()
				+ this.getPaddingTop() + this.getPaddingBottom();
		// 模式为 fill_parent和固定宽高的
		if (sizeWidth == MeasureSpec.EXACTLY) {
			measuredWidth = sizeWidth;
		}
		if (sizeHeight == MeasureSpec.EXACTLY) {
			measuredWidth = sizeHeight;
		}

		this.setMeasuredDimension(measuredWidth, measuredHeight);
		// super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	public void draw(Canvas canvas) {
		int left = this.getPaddingLeft();
		int top = this.getPaddingTop();

		for (int i = 0; i < maxVolume - currentVolume; i++) {
			top = i * 2 * bitmap_gray.getHeight() + this.getPaddingTop();
			canvas.drawBitmap(bitmap_gray, left, top, null);
		}

		for (int i = maxVolume - currentVolume; i < maxVolume; i++) {
			top = i * 2 * bitmap_gray.getHeight() + this.getPaddingTop();
			canvas.drawBitmap(bitmap_green, left, top, null);
		}

		super.draw(canvas);
	}

}
