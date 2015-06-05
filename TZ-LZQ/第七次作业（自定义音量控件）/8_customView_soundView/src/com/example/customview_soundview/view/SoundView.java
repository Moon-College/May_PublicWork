package com.example.customview_soundview.view;

import com.example.customview_soundview.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * �Զ��������ؼ�
 * 
 * @author L_ZQ
 * 
 */
public class SoundView extends View {

	private Bitmap bitmap_gray;
	private Bitmap bitmap_green;
	public AudioManager am;
	private int maxVolume;
	private int currentVolume;
	private int down_y;

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
		// ��ʼ��AudioManager
		am = (AudioManager) context.getSystemService(context.AUDIO_SERVICE);
		// ������� ý������
		maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		// ��ǰ����
		currentVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);

		// Button
	}

	// �������
	// widthMeasureSpec��heightMeasureSpec�ߴ��ģʽ��ϵ�һ��ֵ ���ײ����õ�λ����
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// ���Ȼ�ȡ���ؼ�ָ���Ŀ�ߺ�ģʽ ��ģʽ��3�У� fillParent ָ��dp warp_content ,
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);// ����ָ��px
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

		// �����wrap_content ��ʱ��������֪���Լ�Ҫ��߶����Ծͻ�͸��ؼ�һ������������Ҫ�Լ�����
		int measuredWidth = bitmap_gray.getWidth() + this.getPaddingLeft()
				+ this.getPaddingRight();

		int measuredHeight = (2 * maxVolume - 1) * bitmap_gray.getHeight()
				+ this.getPaddingTop() + this.getPaddingBottom();

		if (modeWidth == MeasureSpec.EXACTLY) { // ģʽ��3�У�����android �� fill_Parent
												// �� �̶�ֵ��ָ��dp
												// ����Ϊһ��.EXACTLY����ǰ�����ֵĹ���
			measuredWidth = sizeWidth;
		}
		if (modeHeight == MeasureSpec.EXACTLY) { // ģʽ��3�У�����android ��
													// fill_Parent �� �̶�ֵ��ָ��dp
													// ����Ϊһ��
			measuredHeight = sizeHeight;
		}

		this.setMeasuredDimension(measuredWidth, measuredHeight);

		// super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	// Ҫ��������
	@Override
	protected void onDraw(Canvas canvas) {
		int top = 0;
		int left = this.getPaddingLeft();
		// ��������ͼƬ
		// ����ͼƬ
		// ���ƻ�ɫͼƬ
		for (int i = 0; i < maxVolume - currentVolume; i++) {
			top = i * bitmap_gray.getHeight() * 2 + this.getPaddingTop();
			canvas.drawBitmap(bitmap_gray, left, top, null);
		}
		// ������ɫͼƬ
		for (int i = maxVolume - currentVolume; i < maxVolume; i++) {
			top = i * bitmap_green.getHeight() * 2 + this.getPaddingTop();
			canvas.drawBitmap(bitmap_green, left, top, null);
		}

		super.onDraw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:

			down_y = (int) event.getY();

			break;
		case MotionEvent.ACTION_MOVE:
			int move_y = (int) event.getY();

			// ����ʱ y���� ��ƫ����
			int offset = Math.abs(down_y - move_y);

			// ����������� �ϵ��˼�������
			int number = (int) (offset * 0.05 / (bitmap_gray.getHeight() * 2) + 0.5);

			if (number != 0) {
				if (down_y - move_y > 0) {

					// if (number <= (maxVolume - currentVolume)) {

					// ��������
					for (int i = 0; i < number; i++) {

						if (currentVolume != maxVolume) {
							currentVolume = currentVolume + 1;
							am.adjustStreamVolume(AudioManager.STREAM_MUSIC,
									AudioManager.ADJUST_RAISE, // ���߻��ǵ���
									AudioManager.FLAG_PLAY_SOUND);// ����������ʱ��
																	// ��һЩģʽ�������𶯣���ʾ��������
							invalidate();
							// }
						}
					}
				} else if (down_y - move_y < 0) {
					// if (number <= currentVolume) {
					// ��������
					for (int i = 0; i < number; i++) {
						if (currentVolume != 0) {
							// invalidate();
							currentVolume = currentVolume - 1;
							am.adjustStreamVolume(AudioManager.STREAM_MUSIC,
									AudioManager.ADJUST_LOWER, // ���߻��ǵ���
									AudioManager.FLAG_PLAY_SOUND);// ����������ʱ��
																	// ��һЩģʽ�������𶯣���ʾ��������
							invalidate();
							// }
						}
					}
				}
			}
			// down_y = (int) event.getY();

			break;
		case MotionEvent.ACTION_UP:

			break;

		default:
			break;
		}

		return super.onTouchEvent(event);
	}
}
