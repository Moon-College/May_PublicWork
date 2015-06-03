package com.tz.customcontrol.view;

import com.tz.customcontrol.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义音量控件
 */
public class SoundView extends View {
	
	/**
	 * 音频管理器
	 */
	public AudioManager am;
	/**
	 * 灰色图片，表示调低音量
	 */
	private Bitmap btp_gray;
	/**
	 * 绿色图片，表示调高音量
	 */
	private Bitmap btp_green;
	/**
	 * 最大音量
	 */
	private int maxVolume;
	/**
	 * 当前音量
	 */
	private int currentVolume;

	public int getCurrentVolume() {
		return currentVolume;
	}

	public void setCurrentVolume(int currentVolume) {
		this.currentVolume = currentVolume;
	}

	public SoundView(Context context, AttributeSet attrs) {
		super(context, attrs);
		//初始化AudioManager
		am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		btp_gray = BitmapFactory.decodeResource(getResources(), R.drawable.gray);
		btp_green = BitmapFactory.decodeResource(getResources(), R.drawable.green);
		//最大音量值
		maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		//当前音量
		currentVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);
	}
	
	/**
	 * 测量
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//首先获取父控件指定的宽高和模式
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
		//如果模式是：warp_content
		int width = btp_gray.getWidth() + this.getPaddingLeft() + this.getPaddingRight();
		int heigth = (2 * maxVolume - 1) * btp_gray.getHeight() + this.getPaddingTop() + this.getPaddingBottom();
		//如果模式是：fill_parent 或   固定值，则是MeasureSpec.EXACTLY模式
		if (modeWidth == MeasureSpec.EXACTLY) {
			width = sizeWidth;
		}
		if (modeHeight == MeasureSpec.EXACTLY) {
			heigth = sizeHeight;
		}
		//手动设置宽高
		this.setMeasuredDimension(width, heigth);
		// super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		int top = this.getPaddingTop();
		int left = this.getPaddingLeft();
		//绘制灰色图片
		for (int i = 0; i < maxVolume - currentVolume; i++) {
			top = i * 2 * btp_gray.getHeight()+this.getPaddingTop();
			canvas.drawBitmap(btp_gray, left, top, null);
		}
		//绘制绿色图片
		for (int i = maxVolume-currentVolume; i < maxVolume; i++) {
			top = i * 2 * btp_green.getHeight()+this.getPaddingTop();
			canvas.drawBitmap(btp_green, left, top, null);
		}
		super.onDraw(canvas);
	}

}
