package com.chris.soundviewdemo;

import com.chris.utils.CLog;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.text.GetChars;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

public class SoundView extends View
{
	private static final String TAG = "SoundView";
	private Context mContext;
	private Canvas mCanvas;
	private AudioManager mAudioManager;
	private WindowManager mWindowManager;
	private int mCurVolume;
	private int mMaxVolume;
	private int mStreamType;
	private Bitmap gray_bitmap, green_bitmap;

	public AudioManager getmAudioManager()
	{
		return mAudioManager;
	}

	public void setmAudioManager(AudioManager mAudioManager)
	{
		this.mAudioManager = mAudioManager;
	}

	public int getmCurVolume()
	{
		return mCurVolume;
	}

	public void setmCurVolume(int mCurVolume)
	{
		this.mCurVolume = mCurVolume;
	}

	public int getmMaxVolume()
	{
		return mMaxVolume;
	}

	public void setmMaxVolume(int mMaxVolume)
	{
		this.mMaxVolume = mMaxVolume;
	}

	public int getmStreamType()
	{
		return mStreamType;
	}

	public void setmStreamType(int mStreamType)
	{
		this.mStreamType = mStreamType;
	}

	public SoundView(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}

	public SoundView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		// 打印开关
				CLog.setDebug(false);
		mContext = context;
		gray_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gray);
		green_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.green);
		mAudioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
		mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
		mStreamType = AudioManager.STREAM_MUSIC;
		mMaxVolume = mAudioManager.getStreamMaxVolume(mStreamType);
		mCurVolume = mAudioManager.getStreamVolume(mStreamType);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		int width_size = MeasureSpec.getSize(widthMeasureSpec);
		int width_mode = MeasureSpec.getMode(widthMeasureSpec);
		int height_size = MeasureSpec.getSize(heightMeasureSpec);
		int height_mode = MeasureSpec.getMode(heightMeasureSpec);
		int widthSize = 0;
		int heightSize = 0;
		CLog.i(TAG, "width_mode = " + width_mode + " height_mode = " + height_mode);
		CLog.i(TAG, "width_size = " + width_size + " height_size = " + height_size);
		switch (width_mode)
		{
		//对应布局中android:layout_width="wrap_content"属性
		case MeasureSpec.AT_MOST:
			widthSize = gray_bitmap.getWidth() + getPaddingLeft() + getPaddingRight();
			break;
		//对应布局中android:layout_width="10dp"属性
		//对应布局中android:layout_width="fill_parent"属性
		case MeasureSpec.EXACTLY:
		case MeasureSpec.UNSPECIFIED:
			widthSize = Math.max(getDefaultSize(mWindowManager.getDefaultDisplay().getWidth(), widthMeasureSpec),
					gray_bitmap.getWidth() + getPaddingLeft() + getPaddingRight());
			break;
		default:
			break;
		}

		switch (height_mode)
		{
		//对应布局中android:layout_height="wrap_content"属性
		case MeasureSpec.AT_MOST:
			heightSize = (2 * mMaxVolume - 1) * green_bitmap.getHeight() + getPaddingTop() + getPaddingBottom();
			break;
		//对应布局中android:layout_height="10dp"属性
		//对应布局中android:layout_height="fill_parent"属性
		case MeasureSpec.EXACTLY:
		case MeasureSpec.UNSPECIFIED:
			heightSize = Math.max(getDefaultSize(mWindowManager.getDefaultDisplay().getHeight(), heightMeasureSpec),
					gray_bitmap.getHeight() * 2 * mMaxVolume + getPaddingTop() + getPaddingBottom());
			break;
		default:
			break;
		}
		CLog.i(TAG, "widthSize = " + widthSize + " heightSize = " + heightSize);
		setMeasuredDimension(widthSize, heightSize);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		int left = getPaddingLeft();
		int top = getPaddingTop();
		int maxVol = mAudioManager.getStreamMaxVolume(mStreamType);
		int curVol = mAudioManager.getStreamVolume(mStreamType);

		setmMaxVolume(maxVol);
		setmCurVolume(curVol);
		mCanvas = canvas;
		CLog.i(TAG, "maxVol = " + maxVol + " curVol = " + curVol);
		CLog.i(TAG, "gray_bitmap height = " + gray_bitmap.getHeight());

		//画灰色音量调，表示未使用音量
		for (int i = maxVol; i > curVol; i--)
		{
			CLog.i(TAG, "i = " + i);
			top += 2 * (maxVol - i) * gray_bitmap.getHeight();
			CLog.i(TAG, "top = " + top);
			canvas.drawBitmap(gray_bitmap, left, top, null);
			top = getPaddingTop();
		}
		CLog.i(TAG, "----------------------------------------");
		CLog.i(TAG, "green_bitmap height = " + green_bitmap.getHeight());

		//画绿色音量调，表示当前音量
		for (int i = curVol; i > 0; i--)
		{
			CLog.i(TAG, "i = " + i);
			top += 2 * (maxVol - i) * green_bitmap.getHeight();
			CLog.i(TAG, "top = " + top);
			canvas.drawBitmap(green_bitmap, left, top, null);
			top = getPaddingTop();
		}

		// TODO Auto-generated method stub
		super.onDraw(canvas);
	}

}
