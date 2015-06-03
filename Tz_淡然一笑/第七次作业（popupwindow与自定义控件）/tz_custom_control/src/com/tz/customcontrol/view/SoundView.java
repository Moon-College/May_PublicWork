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
 * �Զ��������ؼ�
 */
public class SoundView extends View {
	
	/**
	 * ��Ƶ������
	 */
	public AudioManager am;
	/**
	 * ��ɫͼƬ����ʾ��������
	 */
	private Bitmap btp_gray;
	/**
	 * ��ɫͼƬ����ʾ��������
	 */
	private Bitmap btp_green;
	/**
	 * �������
	 */
	private int maxVolume;
	/**
	 * ��ǰ����
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
		//��ʼ��AudioManager
		am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		btp_gray = BitmapFactory.decodeResource(getResources(), R.drawable.gray);
		btp_green = BitmapFactory.decodeResource(getResources(), R.drawable.green);
		//�������ֵ
		maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		//��ǰ����
		currentVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);
	}
	
	/**
	 * ����
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//���Ȼ�ȡ���ؼ�ָ���Ŀ�ߺ�ģʽ
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
		//���ģʽ�ǣ�warp_content
		int width = btp_gray.getWidth() + this.getPaddingLeft() + this.getPaddingRight();
		int heigth = (2 * maxVolume - 1) * btp_gray.getHeight() + this.getPaddingTop() + this.getPaddingBottom();
		//���ģʽ�ǣ�fill_parent ��   �̶�ֵ������MeasureSpec.EXACTLYģʽ
		if (modeWidth == MeasureSpec.EXACTLY) {
			width = sizeWidth;
		}
		if (modeHeight == MeasureSpec.EXACTLY) {
			heigth = sizeHeight;
		}
		//�ֶ����ÿ��
		this.setMeasuredDimension(width, heigth);
		// super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		int top = this.getPaddingTop();
		int left = this.getPaddingLeft();
		//���ƻ�ɫͼƬ
		for (int i = 0; i < maxVolume - currentVolume; i++) {
			top = i * 2 * btp_gray.getHeight()+this.getPaddingTop();
			canvas.drawBitmap(btp_gray, left, top, null);
		}
		//������ɫͼƬ
		for (int i = maxVolume-currentVolume; i < maxVolume; i++) {
			top = i * 2 * btp_green.getHeight()+this.getPaddingTop();
			canvas.drawBitmap(btp_green, left, top, null);
		}
		super.onDraw(canvas);
	}

}
