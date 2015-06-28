package com.vincen.qq_slidingmenu.view;

import android.annotation.SuppressLint;
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

public class MySlidingMenu extends HorizontalScrollView{
	
	private int widthPixls;  //屏幕的宽度
	private int mMenuWidth;  //菜单的宽度
	//private int mContentWidth ;  //内容的宽度
	private ViewGroup mMenu;
	private ViewGroup mContent;
	
	private Handler handler = new Handler(){
		
		@Override
		public void handleMessage(Message msg) {
			float scrollX = (Float) msg.obj; 
			if(scrollX >= 0.4*widthPixls){
				smoothScrollTo(widthPixls, 0);
			}else{
				
				smoothScrollTo(0, 0);
			}
		}
	};
	
	public MySlidingMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		//计算屏幕的宽度
		WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metrics = new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(metrics);
		widthPixls = metrics.widthPixels;
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//设置菜单和内容的宽度
		LinearLayout ll = (LinearLayout) this.getChildAt(0);
		mMenu = (ViewGroup) ll.getChildAt(0);
		mContent = (ViewGroup) ll.getChildAt(1);
		
		//如果设置菜单的最大显示宽度
		mMenuWidth =  (int) (0.8*widthPixls);
		mMenu.getLayoutParams().width = (int)(0.8*widthPixls) ;
		mContent.getLayoutParams().width = widthPixls;
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if(changed){
			Log.i("INFO", mMenuWidth+"");
			this.scrollTo(mMenuWidth, 0);
		}
	}
	
	@SuppressLint("NewApi")
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		
		float scale = (float)l/mMenuWidth;
		//向左移动时的缩放 1~0.7
		float leftScale = 1-scale*0.3f;
		//设置菜单向左缩放的比例
		mMenu.setScaleX(leftScale);
		mMenu.setScaleY(leftScale);
		
		//透明度变化1~0.3
		float leftAlpha = 1 - scale*0.7f;
		mMenu.setAlpha(leftAlpha);
		
		//设置菜单向左滑动的位移变化
		mMenu.setTranslationX(1*0.7f);
		
		//设置内容的变化mContent变化 0.8~1.0
		float rightScale = 0.8f+0.2f*scale;
		mContent.setScaleX(rightScale);
		mContent.setScaleY(rightScale);
		
		super.onScrollChanged(l, t, oldl, oldt);
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		
		
		case MotionEvent.ACTION_UP:
			
			//手指松开屏幕滑动的距离
			float scrollX = this.getScrollX();
			Message message =handler.obtainMessage();
			message.obj = scrollX;
			message.sendToTarget();
			
			break;

		default:
			break;
		};
		return super.onTouchEvent(ev);
	}

}
