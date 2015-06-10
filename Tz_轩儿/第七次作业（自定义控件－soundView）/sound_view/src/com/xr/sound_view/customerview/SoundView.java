package com.xr.sound_view.customerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.View;

import com.xr.sound_view.R;

public class SoundView extends View {

	private Bitmap bitmap_gray;
	private Bitmap bitmap_green;
	public AudioManager am;
	private int maxVolum;
	private int currentVolum;
	
    public int getCurrentVolume(){
    	return currentVolum;
    }
    public void setCurrentVolume(int currentVolum){
    	this.currentVolum = currentVolum;
    }
	public SoundView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		bitmap_gray = BitmapFactory.decodeResource(getResources(),
				R.drawable.gray);
		bitmap_green = BitmapFactory.decodeResource(getResources(),
				R.drawable.green);
		// 初始化
		am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		// 获取最大音量
		maxVolum = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		// 获取当前音量
		currentVolum = am.getStreamVolume(AudioManager.STREAM_MUSIC);
	}
	
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    	
    	//获取父类的宽高和模式
    	int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
    	int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
    	int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
    	int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
    	//如果为wrap_content模式
    	int measuredWidth = bitmap_gray.getWidth()+this.getPaddingLeft()+this.getPaddingRight();
    	int measuredHeight = ( 2 * maxVolum - 1 ) * bitmap_gray.getHeight()+this.getPaddingTop()+this.getPaddingBottom();
    	//选择哪种模式
    	if( modeWidth == MeasureSpec.EXACTLY){
    		measuredWidth = sizeWidth; 
    	}
    	if( modeHeight == MeasureSpec.EXACTLY){
    		measuredHeight = sizeHeight;
    	}
    	 //如果不属于exactly模式，就属于wrap_content模式，所以手动设置宽高
    	this.setMeasuredDimension(measuredWidth, measuredHeight);
	}
    
    
    
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		// super.onDraw(canvas);

		// 画图
		//int top = 0;
		int top = this.getPaddingTop();
		int left= this.getPaddingLeft();
		//int top = this.getPaddingTop();
		// 画灰线
		for (int i = 0; i < maxVolum - currentVolum; i++) {
			top = i * 2 * bitmap_green.getHeight()+this.getPaddingTop();
			canvas.drawBitmap(bitmap_gray, left, top, null);
			// 画绿线
			for (int i1 = maxVolum - currentVolum; i1 < maxVolum; i1++) {
				top = i1 * 2 * bitmap_gray.getHeight()+this.getPaddingTop();
				canvas.drawBitmap(bitmap_green, left, top, null);
			}

		}

	}
}
