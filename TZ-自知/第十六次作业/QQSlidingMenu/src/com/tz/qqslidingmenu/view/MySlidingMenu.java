package com.tz.qqslidingmenu.view;
import com.nineoldandroids.view.ViewHelper;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;


public class MySlidingMenu extends HorizontalScrollView{
	private int width;
	private int mMenuWidth;
	private int mContentWidth;
	private ViewGroup mMenu;
	private ViewGroup mContent;
	private final float mMenuWidthOut = 0.2f;
	public MySlidingMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics displayMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(displayMetrics);
		width = displayMetrics.widthPixels;
	}
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		LinearLayout ll = (LinearLayout) this.getChildAt(0);
		mMenu =  (ViewGroup) ll.getChildAt(0);
		mContent = (ViewGroup) ll.getChildAt(1);

		mMenuWidth =  (int) (0.8*width);
		mMenu.getLayoutParams().width = (int)(0.8*width);
		mContent.getLayoutParams().width = width;
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if(changed){
			this.scrollTo(mMenuWidth, 0);
		}
	}
	
	private int localX;
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		localX = l;
		float scale = (float)l/mMenuWidth;

		float leftScale = 1-scale*0.3f;

		ViewHelper.setScaleX(mMenu, leftScale);
		ViewHelper.setScaleY(mMenu, leftScale);
		
		float leftAlpha = 1-scale*0.7f;
		ViewHelper.setAlpha(mMenu, leftAlpha);
		
		ViewHelper.setTranslationX(mMenu, l*0.7f);
		
		float rightScale = 0.8f+0.2f*scale;
		ViewHelper.setScaleX(mContent, rightScale);
		ViewHelper.setScaleY(mContent, rightScale);
		
		super.onScrollChanged(l, t, oldl, oldt);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_UP:
			if(localX >= (mMenuWidth/2) ) {
				MySlidingMenu.this.scrollTo(mMenuWidth, 0);
			} else {
				MySlidingMenu.this.scrollTo(0, 0);
			}
			break; 
		default:
			break;
		}
		return super.onTouchEvent(ev);
	}
}
