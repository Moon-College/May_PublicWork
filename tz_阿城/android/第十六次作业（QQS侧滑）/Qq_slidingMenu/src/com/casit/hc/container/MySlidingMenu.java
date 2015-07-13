package com.casit.hc.container;

import com.nineoldandroids.view.ViewHelper;

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
import android.widget.LinearLayout;

public class MySlidingMenu extends HorizontalScrollView {

	private int widthPixsl;
	private int mMenuWidth;
	private int mContentWidth;
	private ViewGroup mMenu;
	public ViewGroup mContent;
	private final float mMenuWidthOut = 0.2f;
	private int moveToX;
	Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			int  scrollX = (Integer) msg.obj;
			MySlidingMenu.this.smoothScrollTo(scrollX,0);
		};
	};
	public MySlidingMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		WindowManager wm = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
		DisplayMetrics displayMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(displayMetrics);
	    widthPixsl = displayMetrics.widthPixels;
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
	    LinearLayout ll = (LinearLayout) this.getChildAt(0);
	    mMenu = (ViewGroup) ll.getChildAt(0);
	    mContent = (ViewGroup) ll.getChildAt(1);
	    //如果设置最小值
	    mMenuWidth = (int) (0.8 * widthPixsl);
	    mMenu.getLayoutParams().width = mMenuWidth;
        mContent.getLayoutParams().width = widthPixsl;
	    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);	
		if(changed){
			this.scrollTo(mMenuWidth, 0);
			Log.i("INFO", ""+mMenuWidth);
		}

	}
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		// TODO Auto-generated method stub
		float scale = (float)l/mMenuWidth;
		float leftScale =1-scale *0.3f;
		float leftAlpha = 1- scale * 0.7f;
		ViewHelper.setScaleX(mMenu, leftScale);
		ViewHelper.setScaleY(mMenu, leftScale);
		ViewHelper.setAlpha(mMenu, leftAlpha);
		ViewHelper.setTranslationX(mMenu, (float) (l*0.7));
		//0.8-1.0
		float rightScale  = 0.8f + 0.2f*scale ;
		mContent.setScaleX(rightScale);
		mContent.setScaleY(rightScale);
		
		super.onScrollChanged(l, t, oldl, oldt);
	}
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		switch (ev.getAction()) {
		case MotionEvent.ACTION_UP:

			Log.i("INFO", "UP");
			float upScrollX  = this.getScrollX();
			Log.i("INFO", upScrollX+"up");
			Log.i("INFO", mMenuWidth+"");
			if(upScrollX<0.4*mMenuWidth){
				
				moveToX = 0;
			}else{
				moveToX = mMenuWidth;
			}
			Message msg = handler.obtainMessage();
			msg.obj =  (int)moveToX;
			msg.sendToTarget();
			break;

		default:
			break;
		}
		return super.onTouchEvent(ev);
	}

}
