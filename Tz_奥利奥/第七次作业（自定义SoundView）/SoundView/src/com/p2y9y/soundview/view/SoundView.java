package com.p2y9y.soundview.view;

import com.p2y9y.soundview.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.View;

public class SoundView extends View {
	
	/**
	 * ��ɫͼƬ
	 */
	Bitmap mGaryBitmap;
	
	/**
	 * ��ɫͼƬ
	 */
	Bitmap mGreenBitmap;
	
	/**
	 * ����������
	 */
	public AudioManager mAudioManager;

	/**
	 * �ֻ�֧�ֵ��������
	 */
	int mMaxVolume;
	
	/**
	 * �ֻ���ǰ������
	 */
	int mCurrentVolume;
	
	public SoundView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		mGaryBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gray);
		mGreenBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.green);
		
		mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		mMaxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		mCurrentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//�õ��ؼ��Ĳ���ģʽ
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		
		//�����wrap_content
		int measuredWidth = mGaryBitmap.getWidth()+getPaddingLeft()+getPaddingRight();
		int measuredHeight = mGaryBitmap.getHeight()*mMaxVolume*2-1+getPaddingTop()+getPaddingBottom();
		
		//�̶�ģʽ
		if(widthMode == MeasureSpec.EXACTLY){
			measuredWidth = widthSize;
		}
		if(heightMode == MeasureSpec.EXACTLY){
			measuredHeight = heightSize;
		}
		this.setMeasuredDimension(measuredWidth, measuredHeight);
	}
		
	@Override
	protected void onDraw(Canvas canvas) {
		//����ɫͼƬ
		int left = getPaddingLeft();
		int top = getPaddingTop();
		for (int i = 0; i < mMaxVolume-mCurrentVolume; i++) {
			canvas.drawBitmap(mGaryBitmap, left, top+mGaryBitmap.getHeight()*i*2, null);	
		}
		//����ɫͼƬ
		for (int i = mMaxVolume-mCurrentVolume; i < mMaxVolume; i++) {
			canvas.drawBitmap(mGreenBitmap, left, top+mGreenBitmap.getHeight()*i*2, null);
		}
		super.onDraw(canvas);
	}


	public void setCurrentVolume() {
		this.mCurrentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
	}
	
	
}
