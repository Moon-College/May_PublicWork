package com.junwen.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.View;

import com.example.custom_view.R;

public class CustomView extends View {

	// 充满音量图标
	private Bitmap bitmap_Full;
	// 空的音量图标
	private Bitmap bitmap_Void;
	// 音频管理器
	private AudioManager am;
	// 最大音量
	private int maxVolume;
	// 当前音量
	private int currVolume;
	// 固定宽度
	private int WIDTH = 20;

	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);

		bitmap_Full = getLowBitmap(R.drawable.pellet_full);
		bitmap_Void = getLowBitmap(R.drawable.pellet);
		am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);
		currVolume = am.getStreamVolume(AudioManager.STREAM_SYSTEM);
	}

	/**
	 * 返回小图片
	 * 
	 * @param bitmap
	 * @return
	 */
	private Bitmap getLowBitmap(int path) {
		Bitmap bitmap;
		BitmapFactory.Options options = new Options();
		options.inJustDecodeBounds = true;
		bitmap = BitmapFactory.decodeResource(getResources(), path, options);
		int outWidth = options.outWidth;
		options.inSampleSize = outWidth / WIDTH;
		options.inJustDecodeBounds = false;
		bitmap = BitmapFactory.decodeResource(getResources(), path, options);
		return bitmap;
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//获取XML布局传过来
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int HeightSize = MeasureSpec.getSize(heightMeasureSpec);
		int HeightMode = MeasureSpec.getMode(heightMeasureSpec);
		
		//这个wrap_content的宽度,自适应就要适应图片的宽度
		int width = bitmap_Full.getWidth()+getPaddingLeft()+getPaddingRight();
		int height = (maxVolume * 2 -1)*bitmap_Full.getHeight()+this.getPaddingTop()+getPaddingBottom();
		if(widthMode == MeasureSpec.EXACTLY) {
			width = widthSize;
		}
		if(HeightMode == MeasureSpec.EXACTLY)
		{
			height = HeightSize;
		}
		setMeasuredDimension(width, height);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		int top = this.getPaddingTop();
		for (int i = 0; i < maxVolume - currVolume; i++) {
			top = i * 2 * bitmap_Full.getHeight();
			canvas.drawBitmap(bitmap_Void, this.getPaddingLeft(), top+getPaddingTop(), null);
		}
		for (int i = maxVolume - currVolume; i < maxVolume; i++) {
			top = i * 2 * bitmap_Full.getHeight();
			canvas.drawBitmap(bitmap_Full, this.getPaddingLeft(), top+getPaddingTop(), null);
		}
	}
	public void setCurrVolume(int currVolume) {
		this.currVolume = currVolume;
	}
}
