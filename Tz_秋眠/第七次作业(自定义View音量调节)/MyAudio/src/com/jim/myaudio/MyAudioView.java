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
	 * �������ֵ
	 */
	private int mMaxVolme;
	/**
	 * ϵͳ��ǰ����ֵ
	 */
	private int mCurrentSystemVome;


	public void setmCurrentSystemVome(int mCurrentSystemVome) {
		this.mCurrentSystemVome = mCurrentSystemVome;
	}

	private int startX;// ��ʼX��λ��
	private int startY;// ��ʼY��λ��
	private int endX;// ����ʱX��λ��
	private int endY;// ����ʱY��λ��

	

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
		// ������ɫ�����Ļ���
		for (int i = 0; i < mMaxVolme - mCurrentSystemVome; i++) {
			top = i * 2 * bitmap_gray.getHeight() + this.getPaddingTop();
			canvas.drawBitmap(bitmap_gray, left + this.getPaddingLeft(), top,
					null);
		}
		// ������ɫ�����Ļ���
		for (int i = mMaxVolme - mCurrentSystemVome; i < mMaxVolme; i++) {
			top = i * 2 * bitmap_green.getHeight() + this.getPaddingTop();
			canvas.drawBitmap(bitmap_green, left + this.getPaddingLeft(), top,
					null);
		}
		super.onDraw(canvas);
	}

	/**
	 * widthMeasureSpec/heightMeasureSpec
	 * ����widthMode/heightMode��widthSize/heightSize���������õ�
	 * ����ͨ��getMode��getSize��ö�Ӧ��ֵ
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		// ��������ݰ��������������С
		int measureWidth = bitmap_gray.getWidth() + this.getPaddingLeft()
				+ this.getPaddingRight();
		int measureHeight = (this.mMaxVolme * 2 - 1) * bitmap_gray.getHeight()
				+ this.getPaddingTop() + this.getPaddingBottom();

		this.startX = this.getPaddingLeft();
		this.startY = this.getPaddingTop();
		this.endX = this.getPaddingLeft() + bitmap_gray.getWidth();
		this.endY = this.getPaddingTop() + (this.mMaxVolme * 2 - 1)
				* bitmap_gray.getHeight();
		// ����������Ϊ�̶�ֵ����ƥ�丸������
		if (widthMode == MeasureSpec.EXACTLY) {
			measureWidth = widthSize;
		}
		// ����߶�����Ϊ�̶�ֵ����ƥ�丸������
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
