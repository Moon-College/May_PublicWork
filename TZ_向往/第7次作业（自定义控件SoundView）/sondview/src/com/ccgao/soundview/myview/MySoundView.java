package com.ccgao.soundview.myview;

import com.ccgao.soundview.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.View;

public class MySoundView extends View {
	private Bitmap grayBitmap;
	private Bitmap greenBitmap;
	public AudioManager am;
	private int maxVolume;
	private int currenVolume;

	public int getCurrenVolume() {
		return currenVolume;
	}

	public void setCurrenVolume(int currenVolume) {
		this.currenVolume = currenVolume;
	}

	public MySoundView(Context context, AttributeSet attrs) {
		super(context, attrs);
		grayBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gray);
		greenBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.green);
		am = (AudioManager) context.getSystemService(context.AUDIO_SERVICE);
		// 最大音量
		maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		// 当前音量
		currenVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

		int measuredWidth = grayBitmap.getWidth() + this.getPaddingLeft() + this.getPaddingRight();
		int measuredHeight = grayBitmap.getHeight() * (maxVolume * 2 - 1) + this.getPaddingTop() + this.getPaddingBottom();
		if (modeWidth == MeasureSpec.EXACTLY) {
			measuredWidth = sizeWidth;
		}
		if (modeHeight == MeasureSpec.EXACTLY) {
			measuredHeight = sizeHeight;
		}
		//手动设置宽高
		this.setMeasuredDimension(measuredWidth, measuredHeight);
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	// 经常要重绘
	@Override
	protected void onDraw(Canvas canvas) {
		// 绘制音量图片
		// 加载图片
		int sLeft = this.getPaddingLeft();
		int sTop = this.getPaddingTop();
		int top;
		// 绘制灰色音量
		for (int i = 0; i < maxVolume - currenVolume; i++) {
			top = grayBitmap.getHeight() * 2 * i + sTop;
			canvas.drawBitmap(grayBitmap, sLeft, top, null);
		}
		// 绘制绿色音量
		for (int i = maxVolume - currenVolume; i < maxVolume; i++) {
			top = greenBitmap.getHeight() * 2 * i + sTop;
			canvas.drawBitmap(greenBitmap, sLeft, top, null);
		}
		super.onDraw(canvas);
	}
}
