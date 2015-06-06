package com.xigua.customvoiceview;


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
	private int measuredWidth,measuredHeight;
	private int modeHeight,modeWidth;
	private int scalewidth,scaleheight;
	
	public SoundView(Context context, AttributeSet attrs) {
		super(context, attrs);
		bitmap_gray = BitmapFactory.decodeResource(getResources(), R.drawable.gray);
		bitmap_green = BitmapFactory.decodeResource(getResources(), R.drawable.green);
		//��ʼ��AudioManager
		am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		//�������ֵ
		maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		//��ǰ����
		currentVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);
	}

	
	//�������
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//���Ȼ�ȡ���ؼ�ָ���Ŀ�ߺ�ģʽ
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
	    modeHeight = MeasureSpec.getMode(heightMeasureSpec);
		
		//�����wrap_content
		if(modeWidth == MeasureSpec.AT_MOST){
		measuredWidth = bitmap_gray.getWidth() + this.getPaddingLeft() + this.getPaddingRight();
		}
		if(modeHeight == MeasureSpec.AT_MOST){
		measuredHeight = (2 * maxVolume - 1) * bitmap_gray.getHeight() + this.getPaddingTop() + this.getPaddingBottom();
		}
		if (modeWidth == MeasureSpec.EXACTLY){
			measuredWidth = sizeWidth ;
		}
		if (modeHeight == MeasureSpec.EXACTLY){
			measuredHeight = sizeHeight ;
		}
		this.setMeasuredDimension(measuredWidth, measuredHeight);//�ֶ����ÿ��
		
	}
	
	//������Ҫ�ػ�
	@Override
	protected void onDraw(Canvas canvas) {
		//��������ͼƬ
		//����ͼƬ
		int left =  this.getPaddingLeft();
		int top = this.getPaddingTop();
		//���ƻ�ɫͼƬ
		for (int i = 0; i < maxVolume - currentVolume; i++) {
			//��ȷ��
			if(modeWidth == MeasureSpec.EXACTLY){
				//��ȷ��
				if(modeHeight == MeasureSpec.EXACTLY){
			top = i * 2 * (measuredHeight-this.getPaddingTop()-this.getPaddingBottom())/(2*maxVolume-1) + this.getPaddingTop();
			scalewidth = measuredWidth-this.getPaddingRight()-this.getPaddingLeft();
			scaleheight = (measuredHeight-this.getPaddingTop()-this.getPaddingBottom())/(2*maxVolume-1);
				}
				//�߲�ȷ��
				if(modeHeight == MeasureSpec.AT_MOST){
					top = i * 2 * bitmap_gray.getHeight()+this.getPaddingTop();
					scalewidth = measuredWidth-this.getPaddingRight()-this.getPaddingLeft();
					scaleheight = bitmap_gray.getHeight();
				}
			Bitmap scalemapgray = Bitmap.createScaledBitmap(bitmap_gray, scalewidth, scaleheight, true);
			canvas.drawBitmap(scalemapgray, left, top, null);
			}
			else if(modeWidth == MeasureSpec.AT_MOST){
				if(modeHeight == MeasureSpec.AT_MOST){
				top = i * 2 * bitmap_gray.getHeight()+this.getPaddingTop();
				scalewidth = bitmap_gray.getWidth();
				scaleheight = bitmap_gray.getHeight();
				}
				if(modeHeight == MeasureSpec.EXACTLY){
					top = i * 2 * (measuredHeight-this.getPaddingTop()-this.getPaddingBottom())/(2*maxVolume-1)+this.getPaddingTop();
					scalewidth = bitmap_gray.getWidth();
					scaleheight = (measuredHeight-this.getPaddingTop()-this.getPaddingBottom())/(2*maxVolume-1);
				}
				Bitmap scalemapgray2 = Bitmap.createScaledBitmap(bitmap_gray, scalewidth, scaleheight, true);
				canvas.drawBitmap(scalemapgray2, left, top, null);
			}
		}
		//������ɫͼƬ
		for (int i = maxVolume - currentVolume; i < maxVolume; i++) {
			if(modeWidth == MeasureSpec.EXACTLY){
				if(modeHeight == MeasureSpec.EXACTLY){
			top = i * 2 * (measuredHeight-this.getPaddingTop()-this.getPaddingBottom())/(2*maxVolume-1) + this.getPaddingTop();
			scalewidth = measuredWidth-this.getPaddingRight()-this.getPaddingLeft();
			scaleheight = (measuredHeight-this.getPaddingTop()-this.getPaddingBottom())/(2*maxVolume-1);
				}
				if(modeHeight == MeasureSpec.AT_MOST){
					top = i * 2 * bitmap_gray.getHeight()+this.getPaddingTop();
					scalewidth = measuredWidth-this.getPaddingRight()-this.getPaddingLeft();
					scaleheight = bitmap_gray.getHeight();
				}
			Bitmap scalemapgreen = Bitmap.createScaledBitmap(bitmap_green, scalewidth, scaleheight, true);
			canvas.drawBitmap(scalemapgreen, left, top, null);
			}
			else if(modeWidth == MeasureSpec.AT_MOST){
				if(modeHeight == MeasureSpec.AT_MOST){
				top = i * 2 * bitmap_gray.getHeight()+this.getPaddingTop();
				scalewidth = bitmap_gray.getWidth();
				scaleheight = bitmap_gray.getHeight();
				}
				if(modeHeight == MeasureSpec.EXACTLY){
					top = i * 2 * (measuredHeight-this.getPaddingTop()-this.getPaddingBottom())/(2*maxVolume-1)+this.getPaddingTop();
					scalewidth = bitmap_gray.getWidth();
					scaleheight = (measuredHeight-this.getPaddingTop()-this.getPaddingBottom())/(2*maxVolume-1);
				}
				Bitmap scalemapgreen2 = Bitmap.createScaledBitmap(bitmap_green, scalewidth, scaleheight, true);
				canvas.drawBitmap(scalemapgreen2, left, top, null);
			}
		}
		super.onDraw(canvas);
	}
	
	public int getCurrentVolume() {
		return currentVolume;
	}

	public void setCurrentVolume(int currentVolume) {
		this.currentVolume = currentVolume;
	}
}
