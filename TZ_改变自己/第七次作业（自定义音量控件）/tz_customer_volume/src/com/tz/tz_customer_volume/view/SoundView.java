package com.tz.tz_customer_volume.view;

import com.tz.tz_customer_volume.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.View;

public class SoundView extends View{

	private Bitmap bitmap_gray;
	private Bitmap bitmap_green;
	public AudioManager am;
	private int maxVolume;
	private int currentVolume;
	
	public void setCurrentVolume(int currentVolume) {
		this.currentVolume = currentVolume;
	}
	public SoundView(Context context, AttributeSet attrs) {
		super(context, attrs);
		bitmap_gray = BitmapFactory.decodeResource(getResources(), R.drawable.gray);
		bitmap_green = BitmapFactory.decodeResource(getResources(), R.drawable.green);
		am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		//�������ֵ
		maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		//��ǰ����
		currentVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);
	
	}
	
	/**
	 * ������Ҫ�ػ�
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		//��������ͼƬ
		int top = 0;
		//���ƻ�ɫͼƬ
		for (int i = 0; i < maxVolume - currentVolume; i++) {
			top = i * 2 * bitmap_gray.getHeight();
			canvas.drawBitmap(bitmap_gray, 20, top, null);
		}
		
		for(int i = maxVolume - currentVolume; i < maxVolume; i++) {
			top = i * 2 * bitmap_green.getHeight();
			canvas.drawBitmap(bitmap_green, 20, top, null);
		}
		
		super.onDraw(canvas);
	}

}
