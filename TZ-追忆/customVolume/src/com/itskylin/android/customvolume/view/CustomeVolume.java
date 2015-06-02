package com.itskylin.android.customvolume.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class CustomeVolume extends View {

	private Paint mPaint;
	private int grayColor = Color.rgb(150, 150, 150);
	private int greenColor = Color.rgb(0, 255, 0);
	// 音量管理器
	private AudioManager am;
	// 声音类别
	private int volumeType = AudioManager.STREAM_MUSIC;
	// 音量块宽度
	private float widthRect;
	// 未激活音量块
	private float grayRect;
	// 激活音量块
	private float greenRect;
	// 最大音量
	private int maxVolume;
	// 当前音量
	private int currentVolume;
	// 记录按下屏幕X坐标
	private float touchX;
	// 记录按下屏幕Y坐标
	private float touchY;

	public CustomeVolume(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public CustomeVolume(Context context, AttributeSet attrs, int defStyleAttr,
			int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		initView(context);
	}

	public CustomeVolume(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView(context);
	}

	public CustomeVolume(Context context) {
		super(context);
		initView(context);
	}

	private void initView(Context context) {
		am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		widthRect = 100;
		grayRect = 20;
		greenRect = 20;

		maxVolume = am.getStreamMaxVolume(volumeType);
		currentVolume = am.getStreamVolume(volumeType);
		mPaint = new Paint();
	}

	/**
	 * volumeType defaulte is AudioManager.MUSIC
	 * 
	 * 
	 * @Description: TODO
	 * @param volumeType
	 * @return void
	 * @author BlueSky QQ：345066543
	 * @date 2015年5月31日
	 */
	public void setVolumeType(int volumeType) {
		this.volumeType = volumeType;
	}

	public void setVolumeUp() {
		am.adjustStreamVolume(volumeType, AudioManager.ADJUST_RAISE,
				AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
		currentVolume = am.getStreamVolume(volumeType);
		if (currentVolume == maxVolume) {
			Toast.makeText(getContext(), "已经是最大音量", Toast.LENGTH_SHORT).show();
			return;
		}
		invalidate();
	}

	public void setVolumeDown() {
		am.adjustStreamVolume(volumeType, AudioManager.ADJUST_LOWER,
				AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
		currentVolume = am.getStreamVolume(volumeType);
		if (currentVolume == 0) {
			Toast.makeText(getContext(), "已经是最小音量", Toast.LENGTH_SHORT).show();
			return;
		}
		invalidate();
	}

	public void setVolume(int nowVolume) {
		currentVolume = am.getStreamVolume(volumeType);
		am.setStreamVolume(volumeType, nowVolume,
				AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
		invalidate();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = MeasureSpec.getSize(widthMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int height = MeasureSpec.getSize(heightMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		float wrapWidth = widthRect + this.getPaddingLeft()
				+ this.getPaddingRight();
		float measuredWidth = widthMode == MeasureSpec.EXACTLY ? width
				: wrapWidth;
		float wrapHeight = (2 * maxVolume - 1) * grayRect
				+ this.getPaddingBottom() + this.getPaddingTop();
		float measuredHeight = heightMode == MeasureSpec.EXACTLY ? height
				: wrapHeight;
		// 得到音量块宽度x
		widthRect = measuredWidth - this.getPaddingRight();
		float viewHeight = measuredHeight - this.getPaddingTop()
				- this.getPaddingBottom();
		// 得到未激活音量块高度
		grayRect = viewHeight * 1.0f / (2 * maxVolume - 1);
		// 得到激活音量块高度
		greenRect = viewHeight * 1.0f / (2 * maxVolume - 1);
		setMeasuredDimension((int) measuredWidth, (int) measuredHeight);
	}

	@Override
	protected void onDraw(Canvas canvas) {

		float left = this.getPaddingLeft();
		float top = this.getPaddingTop();

		for (int i = 0; i < maxVolume; i++) {
			if (i < maxVolume - currentVolume) {
				mPaint.setColor(grayColor);
				top = i * 2 * grayRect + this.getPaddingTop();
				RectF rect = new RectF(left, top, widthRect, top + grayRect);
				canvas.drawRoundRect(rect, 9.5f, 9.5f, mPaint);
			} else {
				mPaint.setColor(greenColor);
				top = i * 2 * greenRect + this.getPaddingTop();
				RectF rect = new RectF(left, top, widthRect, top + greenRect);
				canvas.drawRoundRect(rect, 9.5f, 9.5f, mPaint);
			}
		}
		super.onDraw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			touchX = event.getRawX();
			touchY = event.getRawY();
			float currentLoc = (touchY - this.getPaddingTop()) / grayRect;
			int current = (int) (maxVolume - currentLoc);
			// 设置当前音量
			this.setVolume(current);
			Log.i("OnTouch", "maxVolume:" + maxVolume);
			Log.i("OnTouch", "currentVolume:" + currentVolume);
			Log.i("OnTouch", "touchY:" + touchY);
			break;
		case MotionEvent.ACTION_UP:
			float endX = event.getX();
			float endY = event.getY();

			if (Math.abs(endX - touchX) > 5) {
				return false;
			}
			if (Math.abs(endY - touchY) > 20) {
				Toast.makeText(getContext(), "touchY:" + endY,
						Toast.LENGTH_SHORT).show();
			}
			break;

		default:
			break;
		}
		return super.onTouchEvent(event);
	}
}