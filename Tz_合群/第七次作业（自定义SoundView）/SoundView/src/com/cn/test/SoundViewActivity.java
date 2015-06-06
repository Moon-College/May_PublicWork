package com.cn.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;

public class SoundViewActivity extends View implements OnClickListener {


	public static AudioManager mAudioManager;
	private int mMaxVolume=0;
	private int mCurrentVolume=0;
	private OnClickListener ocl;
	private Bitmap mDefaultBitmap;
	private Bitmap mCurrentBitmao;
	private View v;
	private int upY;
	private int height=0;
	private int count=0;
	public SoundViewActivity(Context context, AttributeSet attrs) {
		super(context, attrs);
		mDefaultBitmap=BitmapFactory.decodeResource(context.getResources(),R.drawable.gray);
		mCurrentBitmao=BitmapFactory.decodeResource(context.getResources(),R.drawable.green);
		//��ʼ��AudioManager
		mAudioManager=(AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		//�������ֵ
		mMaxVolume=mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		//��ǰ����
		mCurrentVolume=mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		//���õ���¼�
		setOnClickListener(this);
		v=this;
	}
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int sizeWidth=MeasureSpec.getSize(widthMeasureSpec);
		int sizeWidthModel=MeasureSpec.getMode(widthMeasureSpec);
		int sizeHeight=MeasureSpec.getSize(heightMeasureSpec);
		int sizeHeightModel=MeasureSpec.getMode(heightMeasureSpec);
		
		/**
		 * �����wrap_content����д���ÿ��
		 */
		int measureWidth=mDefaultBitmap.getWidth()+this.getPaddingLeft()+this.getPaddingRight();
		int measureHeight=(2*mMaxVolume-1)*mDefaultBitmap.getHeight()+this.getPaddingTop()+this.getPaddingBottom();
		
		if(sizeWidthModel==MeasureSpec.EXACTLY)
		{
			measureWidth=sizeWidth;
		}
		if(sizeHeightModel==MeasureSpec.EXACTLY)
		{
			measureHeight=sizeHeight;
		}
		this.setMeasuredDimension(measureWidth, measureHeight); 
	}
	@Override
	protected void onDraw(Canvas canvas) {
		drawBitmap(canvas);
		super.onDraw(canvas);
	}

	/**
	 *  ����ͼƬ
	 * @param canvas
	 */
	private void drawBitmap(Canvas canvas) {
		int left=this.getPaddingLeft();
		int top=this.getPaddingTop();
		/**
		 * ��ɫ��ͼƬ
		 */
		for(int i=0;i<mMaxVolume-mCurrentVolume;i++)
		{
			top=this.getPaddingTop()+i*2*mDefaultBitmap.getHeight();
			canvas.drawBitmap(mDefaultBitmap, left,top,null);
		}

		/**
		 * ��ɫ��ͼƬ
		 */
		for(int i=mMaxVolume-mCurrentVolume;i<mMaxVolume;i++)
		{
			top=this.getPaddingTop()+i*2*mCurrentBitmao.getHeight();
			canvas.drawBitmap(mCurrentBitmao, left,top,null);
		}		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_MOVE:
			doChange(event);
			break;
		case MotionEvent.ACTION_UP:
			doChange(event);
			break;

		default:
			break;
		}
		return super.onTouchEvent(event);
	}
	/**
	 * �ı�UI����ʾ
	 * @param event
	 */
	private void doChange(MotionEvent event) {
		upY=(int) event.getY();
		height=upY-v.getPaddingTop();			
		if(height>0)
		{
			count=(height/mCurrentBitmao.getHeight())/2;
		}
		mCurrentVolume=mMaxVolume-count;
		if(mCurrentVolume<0)
		{
			mCurrentVolume=0;
		}
		invalidate();		
		/**
		 * �ı�ϵͳ������С
		 */
		mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mCurrentVolume,  AudioManager.FLAG_PLAY_SOUND);
	}
	
	public void onClick(View v){
		if(ocl!=null)
		{
			ocl.onClick(v);
		}
	}
	
	
	public OnClickListener getOcl() {
		return ocl;
	}
	public void setOcl(OnClickListener ocl) {
		this.ocl = ocl;
	}
	/**
	 * ��ȡĬ��ͼƬ
	 * @return
	 */
	public Bitmap getmDefaultBitmap() {
		return mDefaultBitmap;
	}
	/**
	 * ����Ĭ��ͼƬ
	 * @return
	 */
	public void setmDefaultBitmap(Bitmap mDefaultBitmap) {
		this.mDefaultBitmap = mDefaultBitmap;
	}

	/**
	 * ��ȡ����ͼƬ
	 * @return
	 */
	public Bitmap getmCurrentBitmao() {
		return mCurrentBitmao;
	}
	/**
	 * ���÷���ͼƬ
	 * @return
	 */
	public void setmCurrentBitmao(Bitmap mCurrentBitmao) {
		this.mCurrentBitmao = mCurrentBitmao;
	}
	/**
	 * ��ȡ��ǰ����
	 * @return
	 */
	public int getmCurrentVolume() {
		return mCurrentVolume;
	}

	/**
	 * ���õ�ǰ����
	 * @param mCurrentVolume
	 */
	public void setmCurrentVolume(int mCurrentVolume) {
		this.mCurrentVolume = mCurrentVolume;
	}

	
}