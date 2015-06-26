package com.dd.qq_slidingmenu;

import com.nineoldandroids.view.ViewHelper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class MySlidingMenu extends HorizontalScrollView {
	private int widthPixls;
	private int mMenuWidth;
	private int mContentWidth;
	private ViewGroup mMenu;
	private ViewGroup mContent;
	private final float mMenuWidthOut = 0.2f;
	private Handler handler = new Handler();
	private float sc;
	
	//����Ӧ���ڿؼ�����Ϻã���������ʹ��
	private Runnable run = new Runnable() {
		
		@Override
		public void run() {
			if (sc > mMenuWidth*0.5) {
				MySlidingMenu.this.smoothScrollTo(mMenuWidth, 0);
			}else {
				MySlidingMenu.this.smoothScrollTo(0, 0);
			}
		}
	};

	public MySlidingMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		WindowManager manager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics displayMetrics = new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(displayMetrics);
		widthPixls = displayMetrics.widthPixels;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		LinearLayout ll = (LinearLayout) getChildAt(0);
		mMenu = (ViewGroup) ll.getChildAt(0);
		mContent = (ViewGroup) ll.getChildAt(1);
		mMenuWidth = (int) (0.8 * widthPixls);
		mMenu.getLayoutParams().width = mMenuWidth;
		mContent.getLayoutParams().width = widthPixls;
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if (changed) {
			this.scrollTo(mMenuWidth, 0);
		}
	}

	@Override
	protected void onScrollChanged(int left, int t, int oldl, int oldt) {
		sc = left;
		Log.v("bb", "ledt="+left);
		//left�ı仯��0-mMenuWidth��scale�ı仯��0-1
		float scale = (float)left/mMenuWidth;
		//leftScale�ı仯��1-0.7
		float leftScale = 1 - scale*0.3f;
		ViewHelper.setScaleX(mMenu, leftScale);
		ViewHelper.setScaleY(mMenu, leftScale);
		
		float leftAlpha = 1 - scale*0.7f;
		ViewHelper.setAlpha(mMenu, leftAlpha);
		
		ViewHelper.setTranslationX(mMenu, left*0.7f);
		
		//mContent�仯��0.8-1
		float rigetScale = 0.8f + 0.2f*scale;
		ViewHelper.setScaleX(mContent, rigetScale);
		ViewHelper.setScaleY(mContent, rigetScale);
		Log.v("home", left+"--mMenu"+mMenuWidth);
		super.onScrollChanged(left, t, oldl, oldt);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_UP:
			handler.post(run);
			break;

		default:
			break;
		}
		return super.onTouchEvent(ev);
	}
}
