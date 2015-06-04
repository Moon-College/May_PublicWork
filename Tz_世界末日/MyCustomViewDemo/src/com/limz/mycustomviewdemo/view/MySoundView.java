package com.limz.mycustomviewdemo.view;

import com.limz.mycustomviewdemo.activity.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.View;

public class MySoundView extends View {

	private Bitmap gray;
	private Bitmap green;
	private Context mContext;
	public AudioManager am;
	private int maxVol;
	private int currentVol;
	
	public static final int DEFINELEFT = 30;
	public static final int DEFINETOP = 10;
	public static final int DEFINEIMAGELEFT = 60;
	
	public MySoundView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		gray = BitmapFactory.decodeResource(getResources(), R.drawable.gray);
		green = BitmapFactory.decodeResource(getResources(), R.drawable.green);
		am = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
		maxVol = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		currentVol = am.getStreamVolume(AudioManager.STREAM_MUSIC);
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		int top = DEFINETOP;
		//��������СͼƬ
		for (int i=0; i < maxVol - currentVol; i++) {
			top = DEFINETOP + 2 * i * gray.getHeight();
			canvas.drawBitmap(gray, DEFINEIMAGELEFT, top, null);
		}
		for (int i=maxVol - currentVol; i < maxVol; i++) {
			top = DEFINETOP + 2 * i * gray.getHeight();
			canvas.drawBitmap(green, DEFINEIMAGELEFT, top, null);
		}
		//�浱ǰ����
		Paint paint = new Paint();
		paint.setARGB(255, 0, 255, 255);
		paint.setTextSize(30);
		canvas.drawText("��ǰ����Ϊ��" + currentVol, 
				DEFINELEFT, top + 4 * gray.getHeight(), paint);
		super.onDraw(canvas);
	}

	/**
	 * ��ȡ�������
	 * @author limingzhu
	 * @return �������
	 */
	public int getMaxVol() {
		return maxVol;
	}

	/**
	 * ���������������ʱ�����ã�
	 * @author limingzhu
	 * @param maxVol
	 */
	public void setMaxVol(int maxVol) {
		this.maxVol = maxVol;
	}

	/**
	 * ��ȡ��ǰ����
	 * @author limingzhu
	 * @return ��ǰ����
	 */
	public int getCurrentVol() {
		return currentVol;
	}

	/**
	 * ���õ�ǰ����
	 * @author limingzhu
	 * @param currentVol ��Ҫ���õ�����ֵ
	 */
	public void setCurrentVol(int currentVol) {
		this.currentVol = currentVol;
	}

}
