package com.rocy.tzclass7;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

public class SoundView extends View {

	private Bitmap green;
	private Bitmap gray;
	private  AudioManager manager;
	private int maxVolume;//最大声音；
	private  int volumeing;//当前声音；

	public SoundView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// 获取图片资源
		green = BitmapFactory.decodeResource(getResources(), R.drawable.green);
		gray = BitmapFactory.decodeResource(getResources(), R.drawable.gray);
		//获取声音管理文件
		manager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		//获取音量
		maxVolume = manager.getStreamMaxVolume(AudioManager.STREAM_RING);
		volumeing = manager.getStreamVolume(AudioManager.STREAM_RING);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// 进行测量
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		
		//根据真实的view画面来取值
		int width=this.getPaddingLeft()+green.getWidth()+this.getPaddingRight();
		int height= this.getPaddingTop()+green.getHeight()*(2*maxVolume-1)+this.getPaddingBottom();
		
		Log.i("info", "最大宽度"+width+"，高度"+height);
		//根据模型来判断高度
		if(widthMode==MeasureSpec.EXACTLY){
			width = widthSize;
		}
		
		if(heightMode==MeasureSpec.EXACTLY){
			height = heightSize;
		}
		setMeasuredDimension(width, height);
	}
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		Log.i("info", "最大音"+maxVolume+",现在音量"+volumeing);
		// 灰色部分
		for (int i = 0; i < maxVolume-volumeing; i++) {
			canvas.drawBitmap(gray, this.getPaddingLeft(), this.getPaddingTop()+(i*2*gray.getHeight()), null);
		}
		for (int i = maxVolume-volumeing; i < maxVolume; i++) {
			canvas.drawBitmap(green, this.getPaddingLeft(), this.getPaddingTop()+(i*2*gray.getHeight()), null);
		} 
		
		super.onDraw(canvas);
	}
	
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
    	if(keyCode==KeyEvent.KEYCODE_VOLUME_UP){
    		Log.i("info", "最大音    声音上升");
    		manager.adjustStreamVolume(AudioManager.STREAM_RING, AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
    		//重新获的当前音量
    		volumeing=manager.getStreamVolume(AudioManager.STREAM_RING);
    		//重绘
    		this.invalidate();  
    		return true;
    	}else if(keyCode==KeyEvent.KEYCODE_VOLUME_DOWN){
    		Log.i("info", "最大音    声音下降");
    		manager.adjustStreamVolume(AudioManager.STREAM_RING, AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
    		volumeing=manager.getStreamVolume(AudioManager.STREAM_RING);
    		this.invalidate();
    		return true;
    	}
    		return super.onKeyUp(keyCode, event);
    		
    }
}
