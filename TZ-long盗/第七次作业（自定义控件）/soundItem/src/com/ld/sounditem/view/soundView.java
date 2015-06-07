package com.ld.sounditem.view;

import com.ld.sounditem.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.VideoView;

/**********************************************
 *@author longdao
 *@E-mail:
 *@qq:
 *@version 2015-6-6下午3:59:19
 *模块：
 *说明：
 ***********************************************
 */

public class soundView extends View{
	
	private Bitmap bm_gray;
	private Bitmap bm_green;
	public AudioManager am;
	private int maxVolume;
	private int currentVolume;
	private int left;
	private int top;
	
	public int getCurrentVolume() {
		return currentVolume;
	}

	public void setCurrentVolume(int currentVolume) {
		this.currentVolume = currentVolume;
	}
	
	public soundView(Context context, AttributeSet attrs) {
		super(context, attrs);
		bm_gray = BitmapFactory.decodeResource(getResources(), R.drawable.gray);
		bm_green = BitmapFactory.decodeResource(getResources(), R.drawable.green);
		am = (AudioManager) context.getSystemService(context.AUDIO_SERVICE);
		
		maxVolume=am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);//最大音量
		currentVolume=am.getStreamVolume(AudioManager.STREAM_MUSIC);//当前音量
		
		
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int sizewidth = MeasureSpec.getSize(widthMeasureSpec);
		int modewidth =MeasureSpec.getMode(widthMeasureSpec);
		int sizeheight = MeasureSpec.getSize(heightMeasureSpec);
		int modeheight =MeasureSpec.getMode(heightMeasureSpec);
		
		
		int msWidth = bm_gray.getWidth()+this.getPaddingLeft()+this.getPaddingRight();
		
		int msHeight = (2*maxVolume-1)*bm_gray.getHeight()+this.getPaddingTop()+this.getPaddingBottom();
		
		if (modewidth == MeasureSpec.EXACTLY) {
			msWidth = sizewidth;
		}
		if (modeheight == MeasureSpec.EXACTLY) {
			msHeight = sizeheight;
		}
		this.setMeasuredDimension(msWidth, msHeight);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		left = this.getPaddingLeft();
		top = this.getPaddingTop();
		for (int i = 0; i < maxVolume-currentVolume; i++) {//灰色图片
			top = i*2*bm_gray.getHeight()+this.getPaddingTop();
			canvas.drawBitmap(bm_gray, left, top, null);
		}
		for (int i = maxVolume-currentVolume; i < maxVolume; i++) {
			top = i*2*bm_green.getHeight()+this.getPaddingTop();
			canvas.drawBitmap(bm_green, left, top, null);
		}
		super.onDraw(canvas);
	}

	
	

}
