package com.jim.myaudio;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyAudioView extends View {

	private Bitmap bitmap_gray;
	private Bitmap bitmap_green;
	public AudioManager mAudioManager;

	/**
	 * 最大音量值
	 */
	private int mMaxVolme;
	/**
	 * 系统当前音量值
	 */
	private int mCurrentSystemVome;


	public void setmCurrentSystemVome(int mCurrentSystemVome) {
		this.mCurrentSystemVome = mCurrentSystemVome;
	}

	private int startX;// 起始X轴位置
	private int startY;// 起始Y轴位置
	private int endX;// 结束时X轴位置
	private int endY;// 结束时Y轴位置

	

	public MyAudioView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initData(context);
	}

	private void initData(Context context) {
		// TODO Auto-generated method stub
		bitmap_gray = BitmapFactory.decodeResource(getResources(),
				R.drawable.gray);
		bitmap_green = BitmapFactory.decodeResource(getResources(),
				R.drawable.green);
		mAudioManager = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);
		mMaxVolme = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		mCurrentSystemVome = mAudioManager
				.getStreamVolume(AudioManager.STREAM_MUSIC);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		int left = 0;
		int top = 0;
		// 制作灰色音量的画布
		for (int i = 0; i < mMaxVolme - mCurrentSystemVome; i++) {
			top = i * 2 * bitmap_gray.getHeight() + this.getPaddingTop();
			canvas.drawBitmap(bitmap_gray, left + this.getPaddingLeft(), top,
					null);
		}
		// 制作绿色音量的画布
		for (int i = mMaxVolme - mCurrentSystemVome; i < mMaxVolme; i++) {
			top = i * 2 * bitmap_green.getHeight() + this.getPaddingTop();
			canvas.drawBitmap(bitmap_green, left + this.getPaddingLeft(), top,
					null);
		}
		super.onDraw(canvas);
	}

	/**
	 * widthMeasureSpec/heightMeasureSpec
	 * 是由widthMode/heightMode和widthSize/heightSize经过运算获得的
	 * 可以通过getMode和getSize获得对应的值
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		// 如果是内容包裹则这样计算大小
		int measureWidth = bitmap_gray.getWidth() + this.getPaddingLeft()
				+ this.getPaddingRight();
		int measureHeight = (this.mMaxVolme * 2 - 1) * bitmap_gray.getHeight()
				+ this.getPaddingTop() + this.getPaddingBottom();

		this.startX = this.getPaddingLeft();
		this.startY = this.getPaddingTop();
		this.endX = this.getPaddingLeft() + bitmap_gray.getWidth();
		this.endY = this.getPaddingTop() + (this.mMaxVolme * 2 - 1)
				* bitmap_gray.getHeight();
		// 如果宽度设置为固定值或者匹配父容器，
		if (widthMode == MeasureSpec.EXACTLY) {
			measureWidth = widthSize;
		}
		// 如果高度设置为固定值或者匹配父容器，
		if (heightMode == MeasureSpec.EXACTLY) {
			measureHeight = heightSize;
		}
		setMeasuredDimension(measureWidth, measureHeight);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		float moveX = event.getX();
		float moveY = event.getY();
		if (moveX >= startX && moveX <= endX && moveY >= startY
				&& moveY <= endY) {
			float index = (moveY - getPaddingTop())
					/ (bitmap_gray.getHeight() * 2);
			mCurrentSystemVome = (int) (mMaxVolme - Math.ceil(index));
			mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
					mCurrentSystemVome, AudioManager.FLAG_PLAY_SOUND
							| AudioManager.FLAG_SHOW_UI);
			invalidate();
			return false;
		}
		return super.onTouchEvent(event);
	}
}
