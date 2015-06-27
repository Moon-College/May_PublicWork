package com.caist.selfcontrol;

import com.caist.hc.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.View;

public class SoundView extends View{
    private Bitmap bitmap_green;
    private Bitmap bitmap_gray;
    public AudioManager am;
    private int maxVolume;
    private int currentVolume;

	public int getCurrentVolume() {
		return currentVolume;
	}

	public void setCurrentVolume(int currentVolume) {
		this.currentVolume = currentVolume;
	}

	public SoundView(Context context, AttributeSet attrs) {
		super(context, attrs);
		bitmap_green = BitmapFactory.decodeResource(getResources(), R.drawable.green);
		bitmap_gray =  BitmapFactory.decodeResource(getResources(), R.drawable.gray);
		am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		currentVolume  = am.getStreamVolume(AudioManager.STREAM_MUSIC);
		
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
		
		int measuredWidth = bitmap_gray.getWidth()+this.getPaddingLeft()+this.getPaddingRight();
		int measureHeight = bitmap_gray.getHeight()+this.getPaddingTop()+this.getPaddingBottom();
		
		if(modeWidth == MeasureSpec.EXACTLY){
			measuredWidth = sizeWidth;
		}
		if(modeHeight == MeasureSpec.EXACTLY){
			measureHeight = modeHeight;
		}		
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
	}	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		int top = 0;
		for (int i = 0; i < maxVolume - currentVolume; i++){
			top = i * bitmap_gray.getHeight()*2;
			canvas.drawBitmap(bitmap_gray, 0, top, null);
		}
		for (int i = maxVolume - currentVolume; i < maxVolume; i++){		
			top = i * bitmap_green.getHeight() * 2;
			canvas.drawBitmap(bitmap_green, 0, top, null);
		}
		
		super.onDraw(canvas);
	}
}
