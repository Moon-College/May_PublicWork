package com.rocy.tzclass15;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.BoringLayout.Metrics;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MySlidingMenu extends HorizontalScrollView  {
    private  DisplayMetrics outMetrics;
	private ViewGroup llMain;
	private ViewGroup rlMenu;
	private int width ;
	private float move;
	private Handler handler =new Handler(){
		public void dispatchMessage(android.os.Message msg) {
			float x2 = (Float) msg.obj; 
			if(x2 >= 0.4*width){
				smoothScrollTo(width, 0);
			}else{
				
				smoothScrollTo(0, 0);
			}
		};
	};
	
	public MySlidingMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		outMetrics = new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(outMetrics);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// 测量
		LinearLayout view = (LinearLayout) this.getChildAt(0);
		
		rlMenu = (ViewGroup)this.findViewById(R.id.rl_menu);
		llMain = (ViewGroup)this.findViewById(R.id.ll_mian);
		rlMenu.getLayoutParams().width = (int)(0.8*outMetrics.widthPixels);
		llMain.getLayoutParams().width = outMetrics.widthPixels;
		width=outMetrics.widthPixels;
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// 开始放置位置
		super.onLayout(changed, l, t, r, b);
		if(changed){
			scrollTo(outMetrics.widthPixels, 0);
		}
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		// TODO Auto-generated method stub
		move = (float)l/width;
		//向右移动
		rlMenu.setTranslationX(l*0.8f);
		//缩放
		rlMenu.setScaleX(1-move*0.5f);
		rlMenu.setScaleY(1-move*0.5f);
		//变透明
		rlMenu.setAlpha(1-move*0.8f);
		//右边
		llMain.setScaleX(0.8f+0.2f*move);
		llMain.setScaleY(0.8f+0.2f*move);
		super.onScrollChanged(l, t, oldl, oldt);
		
	}
	
		@Override
		public boolean onTouchEvent(MotionEvent ev) {
			// TODO Auto-generated method stub
			switch (ev.getAction()) {
			
				
			case MotionEvent.ACTION_UP:
				
				//松开的时候
				float x2 = this.getScrollX();
				Message message =handler.obtainMessage();
				message.obj = x2;
				message.sendToTarget();
				
				break;

			default:
				break;
			};
			return super.onTouchEvent(ev);
		}
}
