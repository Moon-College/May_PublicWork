package com.decent.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.decent.volumecontrol.R;

public class DecentVolumeView extends View
{
    private Bitmap mBitmap_gray;
    private Bitmap mBitmap_green;
    private AudioManager mAudioManager;
	private int mMaxVolme;
    /*
     * ���ڼ�¼ϵͳ������
     */
	private int mCurrentSystemVolme;
	/*
	 * ������ʾ���󲿷�ʱ��mCurrentSystemVolme��mCurrentDipslayVolme��һ����
	 * ������onTouch��ʱ��mCurrentDipslayVolme��mCurrentSystemVolme��һ����
	 * ��onTouch��������֮��mCurrentDipslayVolme�����õ�ϵͳ�У�������һ����
	 */
	private int mCurrentDipslayVolme;
    private static final String TAG = "DecentVolumeView";
	public Bitmap getmBitmap_gray()
	{
		return mBitmap_gray;
	}

	public void setmBitmap_gray(Bitmap mBitmap_gray)
	{
		this.mBitmap_gray = mBitmap_gray;
	}

	public Bitmap getmBitmap_green()
	{
		return mBitmap_green;
	}

	public void setmBitmap_green(Bitmap mBitmap_green)
	{
		this.mBitmap_green = mBitmap_green;
	}

	public AudioManager getmAudioManager()
	{
		return mAudioManager;
	}

	public void setmAudioManager(AudioManager mAudioManager)
	{
		this.mAudioManager = mAudioManager;
	}

	public int getmMaxVolme()
	{
		return mMaxVolme;
	}

	public void setmMaxVolme(int mMaxVolme)
	{
		this.mMaxVolme = mMaxVolme;
	}
	public int getmCurrentSystemVolme()
	{
		return mCurrentSystemVolme;
	}

	public void setmCurrentSystemVolme(int mCurrentSystemVolme)
	{
		this.mCurrentSystemVolme = mCurrentSystemVolme;
	}

	public int getmCurrentDipslayVolme()
	{
		return mCurrentDipslayVolme;
	}

	public void setmCurrentDipslayVolme(int mCurrentDipslayVolme)
	{
		this.mCurrentDipslayVolme = mCurrentDipslayVolme;
	}
	

	public DecentVolumeView(Context context)
	{
		super(context);
		initData(context);
		// TODO Auto-generated constructor stub
	}

	public DecentVolumeView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		initData(context);

		// TODO Auto-generated constructor stub
	}


	public DecentVolumeView(Context context, AttributeSet attrs,int style)
	{
		super(context, attrs, style);
		initData(context);
		// TODO Auto-generated constructor stub
	}

    private void initData(Context context)
    {
    	Log.d("INFO", "into initData");
		/*
		 *��ʼ��ͼ�� 
		 */
		mBitmap_gray = BitmapFactory.decodeResource(getResources(), R.drawable.gray);
		mBitmap_green = BitmapFactory.decodeResource(getResources(), R.drawable.green);
		
		/*
		 * ��ʼ��mAudioManger����������͵�ǰ����
		 */
		mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		mMaxVolme = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		mCurrentDipslayVolme = mCurrentSystemVolme = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
    }
	
	@Override
	protected void onDraw(Canvas canvas)
	{
		Log.d("onDraw", "into onDraw mMaxVolme="+mMaxVolme);
		int top = 0;
		/*
		 * ����mCurrentDipslayVolme����
		 * ��������:����paddingTop,����paddingTop�õ�һ��topBase��ֵ���ڴ˻����ϼ���top
		 * ��������:
		 */
		int topBase = getPaddingTop();
		/*
		 * ����mCurrentDipslayVolme����
		 * ��������:����paddingLeft���������������еģ���ֱ��ʹ��
		 * ��������:
		 */
		int left = getPaddingLeft();
		int leftBase=0;
		
		// TODO Auto-generated method stub
		for(int i=0; i<mMaxVolme-mCurrentDipslayVolme; i++)
		{
			top = topBase+2*i*mBitmap_gray.getHeight();
			canvas.drawBitmap(mBitmap_gray, left, top, null);
		}
		
		for(int i=mMaxVolme-mCurrentDipslayVolme; i<mMaxVolme; i++)
		{
			top = topBase+2*i*mBitmap_green.getHeight();
			canvas.drawBitmap(mBitmap_green, left, top, null);			
		}
		super.onDraw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		boolean isNeedUpdateView = false;
		/*
		 * rawX��rawY���������Ļ������
		 */
		float rawX = event.getRawX();
		float rawY = event.getRawY();
		/*
		 * x,y�������view������
		 */
		float x = event.getX();
		float y = event.getY();
		Log.d(TAG, "rawX="+rawX+",rawY="+rawY+",x="+x+",y="+y);
		switch(event.getAction())
		{
			case MotionEvent.ACTION_DOWN:
				Log.d(TAG, "ACTION_DOWN");
				/*
				 * ���ݵ���������������view������ʾ������
				 */
				isNeedUpdateView = updateCurrentDipslayVolme(x,y);
                if(isNeedUpdateView)
                {
                	this.invalidate();
                }
		        break;
			case MotionEvent.ACTION_MOVE:
				Log.d(TAG, "ACTION_MOVE");
				/*
				 * ���ݵ���������������view������ʾ������
				 */
				isNeedUpdateView = updateCurrentDipslayVolme(x,y);
                if(isNeedUpdateView)
                {
                	this.invalidate();
                }

				break;		        
			case MotionEvent.ACTION_UP:
				Log.d(TAG, "ACTION_UP");
				int adjustCount = 0;
				int adjustDirection = 0;
				if(mCurrentSystemVolme>mCurrentDipslayVolme)
				{
					adjustCount = mCurrentSystemVolme - mCurrentDipslayVolme;
					adjustDirection = AudioManager.ADJUST_LOWER;
				}
				else
				{
					adjustCount = mCurrentDipslayVolme - mCurrentSystemVolme;
					adjustDirection = AudioManager.ADJUST_RAISE;
				}
				Log.d(TAG,"before adjust mCurrentSystemVolme="+mCurrentSystemVolme+",mCurrentDipslayVolme="+mCurrentDipslayVolme);
				/*
				 * ���ݵ���������������ϵͳ����
				 */
				for(int i=0;i<adjustCount;i++)
				{
				    mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, adjustDirection, AudioManager.FLAG_PLAY_SOUND);
				}
				mCurrentDipslayVolme = mCurrentSystemVolme = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
				Log.d(TAG,"after adjust mCurrentSystemVolme="+mCurrentSystemVolme+",mCurrentDipslayVolme="+mCurrentDipslayVolme);
				break;
			default:
				break;
		}
		// TODO Auto-generated method stub
		/**
		 * ֻ����MotionEvent.ACTION_DOWN��ʱ�򷵻�true���Ż��������MotionEvent.ACTION_MOVE
		 * ��MotionEvent.ACTION_UP
		 */
		return true;
	}

	private boolean updateCurrentDipslayVolme(float x, float y)
	{
		// TODO Auto-generated method stub
		/*
		 * ���ݵ���������������view������ʾ������
		 */
		if(y>=getPaddingTop() && y<=2*mMaxVolme*mBitmap_gray.getHeight()+getPaddingTop())
		{
           mCurrentDipslayVolme = mMaxVolme- (int) ((y-getPaddingTop())/(mBitmap_gray.getHeight()*2)+1);
           mCurrentDipslayVolme = mCurrentDipslayVolme<0?0:mCurrentDipslayVolme;
           return true;
		}
		else if(y>0 && y<getPaddingTop())
		{
			mCurrentDipslayVolme = 15;
			this.invalidate();
			return true;
		}		
		return false;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		/* MeasureSpec.getMode��ȡMODE
		 * MeasureSpec.getSize��ȡ��Ⱥ͸߶�
		 * ��ȡ�ؼ���ʵ�ʿ�ߺ����õ�MODE����fill_parent,wrap_content,ʵ�ʴ�С?����
		 * Ȼ��ͨ��super.onMeasure���²���
		 */
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		
		int width;  
        int height; 
		/*
		 * MeasureSpec.EXACTLY������ͼϣ������ͼ�Ĵ�СӦ����specSize��ָ���ġ�
         * MeasureSpec.AT_MOST������ͼ�Ĵ�С�����specSize��ָ����ֵ��Ҳ����˵����������ͼ�Ĵ�С����specSize�и�����ֵ��
         * MeasureSpec.UNSPECIFIED�����ǿ�������ָ����ͼ�Ĵ�С��
		 */
		if(widthMode == MeasureSpec.EXACTLY)
		{
			Log.d("INFO", "widthMode==MeasureSpec.EXACTLY");
			width = widthSize;
		}
		else if(widthMode == MeasureSpec.UNSPECIFIED)
		{
			Log.d("INFO", "widthMode==MeasureSpec.UNSPECIFIED");
			width = mBitmap_gray.getWidth()+getPaddingLeft()+getPaddingRight();
		}
		else 
		{
			Log.d("INFO", "widthMode==MeasureSpec.AT_MOST");
			width = mBitmap_gray.getWidth()+getPaddingLeft()+getPaddingRight();
		}
		
        if(heightMode == MeasureSpec.EXACTLY)
        {
        	Log.d("INFO", "heightMode == MeasureSpec.EXACTLY");
        	height =  heightSize;
        }
        else if(heightMode == MeasureSpec.EXACTLY)
        {
        	Log.d("INFO", "heightMode == MeasureSpec.EXACTLY");
    		height =  (mMaxVolme*2-1)*mBitmap_gray.getHeight()+getPaddingTop()+getPaddingBottom();
        }
        else
        {
        	Log.d("INFO", "heightMode == MeasureSpec.AT_MOST");
        	height =  (mMaxVolme*2-1)*mBitmap_gray.getHeight()+getPaddingTop()+getPaddingBottom();
        }

		setMeasuredDimension(width, height);
		// TODO Auto-generated method stub
		//super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	/**
	 * ��ȡ���µ�������Ȼ�������ͼ
	 */
	public void updateView()
	{
		//��ȡ���µ�������Ȼ�������ͼ
		mCurrentSystemVolme = mCurrentDipslayVolme = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		this.invalidate();
	}


}
