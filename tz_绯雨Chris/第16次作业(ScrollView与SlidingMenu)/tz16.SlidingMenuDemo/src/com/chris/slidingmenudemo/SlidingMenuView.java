package com.chris.slidingmenudemo;

import com.chris.slidingmenudemo.listener.OnSlidingChangeListener;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SlidingMenuView extends HorizontalScrollView
{
	private static final String tag = "SlidingMenuView";
	private OnSlidingChangeListener onSlidingChangeListener = null;
	private WindowManager wm;

	private int displayWidth, displayHeight;
	private int smvWidth, smvHeight;
	private int menuWidth,menuHeight, contentWidth, contentHeight;

	private Handler handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			int scrollOffset = (Integer) msg.obj;
			Log.d(tag, "scrollOffset = " + scrollOffset);
			SlidingMenuView.this.smoothScrollTo(scrollOffset, 0);
			Log.d(tag, "1");
		};
	};
	
	private LinearLayout main_layout;
	private ViewGroup menu;
	private ImageView content;

	public OnSlidingChangeListener getOnSlidingChangeListener()
	{
		return onSlidingChangeListener;
	}

	public void setOnSlidingChangeListener(OnSlidingChangeListener onSlidingChangeListener)
	{
		this.onSlidingChangeListener = onSlidingChangeListener;
	}

	public SlidingMenuView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(dm);
		displayWidth = dm.widthPixels;
		displayHeight = dm.heightPixels;
		Log.d(tag, "displayWidth = " + displayWidth);
		Log.d(tag, "displayHeight =" + displayHeight);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		smvWidth = MeasureSpec.getSize(widthMeasureSpec);
		smvHeight = MeasureSpec.getSize(heightMeasureSpec);
		Log.d(tag, "hsv width = " + smvWidth);
		Log.d(tag, "hsv height = " + smvHeight);
		
//		main_layout = (LinearLayout) findViewById(R.id.main_layout);
//		menu = (ViewGroup) main_layout.getChildAt(0);
//		content = (ImageView) main_layout.getChildAt(1);
		//与上面方法等效
		main_layout = (LinearLayout) this.getChildAt(0);
		menu = (ViewGroup) main_layout.findViewById(R.id.menu);
		content = (ImageView) main_layout.findViewById(R.id.content);

		//这里应该用控件的尺寸去适配，而不是屏幕的尺寸
		menuWidth = menu.getLayoutParams().width = (int) (smvWidth * 0.8);
		menuHeight = menu.getLayoutParams().height = smvHeight;
		contentWidth = content.getLayoutParams().width = (int) (smvWidth);
		contentHeight = content.getLayoutParams().height = smvHeight;

		Log.d(tag, "menu width = " + menuWidth);
		Log.d(tag, "menu height =" + menuHeight);
		Log.d(tag, "content width = " + contentWidth);
		Log.d(tag, "content height =" + contentHeight);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b)
	{
		Log.d(tag, "onLayout-> changed=" + changed + " l=" + l + " t=" + t + " r=" + r + " b=" + b);
		super.onLayout(changed, l, t, r, b);
		//最开始就要滑动到content的页面
		if(changed)
		{
			this.scrollTo(menu.getLayoutParams().width, 0);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev)
	{
		switch (ev.getAction())
		{
		case MotionEvent.ACTION_DOWN:

			break;
		case MotionEvent.ACTION_MOVE:

			break;
		case MotionEvent.ACTION_UP:
			int scrollDistence = this.getScrollX();
			int xOffset = 0;
			if (scrollDistence <= (2*menuWidth/3))
			{
				//显示菜单
				xOffset = 0;
			} else
			{
				//隐藏菜单
				xOffset = (int) (smvWidth * 0.8);
			}

			//发送消息，延时到HorizontalScrollView的惯性之后去指定滑动位置，避免惯性影响到指定的距离
			Message msg = new Message();
			msg.obj = xOffset;
//			handler.dispatchMessage(msg); //dispathcMessage会立刻执行handler的handleMessage方法，起不到延时执行的作用
			handler.sendMessage(msg);

			break;

		default:
			break;
		}
		return super.onTouchEvent(ev);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt)
	{
		Log.d(tag, "onScrollChanged-> l="+l+" t="+t+" oldl="+oldl+" oldt="+oldt);
		float scaleRate = (float)l/menuWidth;	// 缩放因子0~1
		float menuSR = (float) (1 - scaleRate/5); //菜单缩放1~0.8倍
		float contentSR = (float) (1.8 - menuSR);	//内容缩放从0.8~1倍
		Log.d(tag, "onScrollChanged-> scaleRate="+scaleRate+" menuSR="+menuSR+" contentSR="+contentSR);
		
		//缩放menu菜单
		menu.setScaleX(menuSR);
		menu.setScaleY(menuSR);
		menu.setAlpha((float) (1-0.2*scaleRate));	//透明度改变1~0.2
		menu.setTranslationX((float) (l*0.8));	//让menu反向移动，形成视差动画效果
		//缩放content图片
		content.setScaleX(contentSR);
		content.setScaleY(contentSR);
		content.setRotation((float) 360*scaleRate);	//自带旋转，装B用
		
		if (onSlidingChangeListener != null)
		{
			onSlidingChangeListener.onSlidingChange(l, t, oldl, oldt);
		}
		super.onScrollChanged(l, t, oldl, oldt);
	}
}
