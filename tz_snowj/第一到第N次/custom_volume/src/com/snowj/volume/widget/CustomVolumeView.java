package com.snowj.volume.widget;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.snowj.volume.R;
import com.snowj.volume.utils.CustomLog;

/**
 * 第七次作业
 * @author lixuejiao
 *
 */
public class CustomVolumeView extends View{


	private Bitmap bitmap_gray;
	private Bitmap bitmap_green;
	private Context context;
	public AudioManager audioManager;
	private int maxVolume;
	private int currentVolume;
	private float viewY;
	private int realWidth;
	private int realHeight;

	public int getCurrentVolume() {
		return currentVolume;
	}

	public void setCurrentVolume(int currentVolume) {
		this.currentVolume = currentVolume;
	}

	public CustomVolumeView(Context context) {
		super(context);
		this.context = context;
		init();
	}

	public CustomVolumeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}

	public CustomVolumeView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		init();
	}

	/** 初始化   */
	private void init() {
		/**
		 * 加载要绘制的图片资源
		 */
		bitmap_gray = BitmapFactory.decodeResource(getResources(), R.drawable.gray);
		bitmap_green = BitmapFactory.decodeResource(getResources(), R.drawable.green);
		
		/**
		 * 获取系统最大音量,当前音量
		 */
		audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		
	}
	
	
	
	@SuppressLint("NewApi")
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		/**
		 * 获取父控件的宽高,
		 * 模式--wrap_content,fill_parent,match_parent
		 */
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		realWidth = bitmap_gray.getWidth()+this.getPaddingLeft()+this.getPaddingRight();
		realHeight = (maxVolume*2-1)*bitmap_gray.getHeight()+this.getPaddingBottom()+this.getPaddingTop();
		/**
		 * fill_parent,match_parent模式下,即为父控件宽高
		 */
		if(widthMode==MeasureSpec.EXACTLY){
			realWidth = widthSize;
		}
		if(heightMode==MeasureSpec.EXACTLY){
			realHeight = heightSize;
		}
		//重新设置
		this.setMeasuredDimension(realWidth, realHeight);
		
	}
	
	
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		int top=this.getPaddingTop();
		int left = this.getPaddingLeft();
		//绘制灰色部分,最大音量
		for (int i = 0; i < maxVolume-currentVolume; i++) {
			//计算与顶部的距离
			top = i*bitmap_gray.getHeight()*2+this.getPaddingTop();
			//参数分别表示,要绘制的图片,绘制的位置距离左边距离,距离底部的距离,画笔-这里不需要
			canvas.drawBitmap(bitmap_gray, left, top, null);
		}
		//绘制绿色部分,表示当前音量
		for (int i = maxVolume-currentVolume; i < maxVolume; i++) {
			top = i*bitmap_gray.getHeight()*2+this.getPaddingTop();
			canvas.drawBitmap(bitmap_green, left, top, null);
		}
		
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			
			/**
			 * 点击改变音量
			 */
			//获取点击的Y坐标
			float y =  event.getY();
			CustomLog.println(Log.DEBUG, "test", "y:"+y);
			for (int i = 0; i < maxVolume; i++) {
				//获取临近的两个图片,的Y坐标
				viewY = i*2*bitmap_gray.getHeight();
				float viewY1 = (i*2-1)*bitmap_gray.getHeight();
				float viewY2 = (i*2+1)*bitmap_gray.getHeight();
				if(y<viewY&&y>viewY1){
					setCurrentVolume(maxVolume-i+1);
					audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume-i, AudioManager.FLAG_PLAY_SOUND);
				}else if(y>viewY&&y<viewY2){
					setCurrentVolume(maxVolume-i);
					audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume-i, AudioManager.FLAG_PLAY_SOUND);
				}
			}
			this.invalidate();
			break;

		default:
			break;
		}
		return super.onTouchEvent(event);
	}
	
}
