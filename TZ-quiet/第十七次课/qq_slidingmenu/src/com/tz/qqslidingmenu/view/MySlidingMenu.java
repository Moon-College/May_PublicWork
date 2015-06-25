package com.tz.qqslidingmenu.view;
import com.nineoldandroids.view.ViewHelper;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;


public class MySlidingMenu extends HorizontalScrollView {
	private int widthPixls;
	private int heightPixls;
	private int mMenuWidth;
	private ViewGroup mMenu;
	private ViewGroup mContent;
	private float hz;
	private Runnable run = new Runnable(){

		public void run() {
			// TODO Auto-generated method stub
			if(hz > 0.5 * mMenuWidth){
				MySlidingMenu.this.smoothScrollTo(mMenuWidth, 0);
			}else{
				MySlidingMenu.this.smoothScrollTo(0, 0);
			}
		}
		
	};
	private Handler handler = new Handler();
	
	public MySlidingMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		//获取屏幕宽度
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(metrics);
		this.widthPixls = metrics.widthPixels;
		this.heightPixls = metrics.heightPixels;
		mMenuWidth = (int) (this.widthPixls*0.8);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		LinearLayout ll = (LinearLayout) this.getChildAt(0);
		mMenu = (ViewGroup) ll.getChildAt(0);
		mMenu.getLayoutParams().width = mMenuWidth;
		mMenu.getLayoutParams().height = heightPixls;
		mContent = (ViewGroup) ll.getChildAt(1);
		mContent.getLayoutParams().width = widthPixls;
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if(changed){
			this.scrollTo(mMenuWidth, 0);
		}
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		hz = l;
		//L的变化范围是0 —— mMenuWidth
		//scale的范围就是0 —— 1
		float scale = (float)l / mMenuWidth;
		ViewHelper.setTranslationX(mMenu, l*0.7f);
		//scale1的变化范围 1——0.7
		float scale1 =1 - 0.3f * scale;
		ViewHelper.setScaleX(mMenu, scale1);
		ViewHelper.setScaleY(mMenu, scale1);
		float scale2 = 0.2f * scale + 0.8f;
		ViewHelper.setScaleX(mContent, scale2);
		ViewHelper.setScaleY(mContent, scale2);
		super.onScrollChanged(l, t, oldl, oldt);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
			handler.post(run);
			break;

		default:
			break;
		}
		return super.onTouchEvent(event);
	}
}
