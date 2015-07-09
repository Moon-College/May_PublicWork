package com.tz.qqslidingmenu.view;

import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.ViewHelper;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;


public class MySlidingMenu extends HorizontalScrollView{
	private int widthPixls;
	private int mMenuWidth;
	private int mContentWidth;
	private ViewGroup mMenu;
	private ViewGroup mContent;
    private final float mMenuWidthOut = 0.2f;

    private static final int MOVE = 112;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == MOVE) {
                int distance = (int) msg.obj;
                if (distance > mMenuWidth / 2) {
                    Log.d("!!", "right");
                    smoothScrollTo(mMenuWidth, 0);
                } else {
                    smoothScrollTo(0, 0);
                    Log.d("!!", "left");
                }
            }
        }
    };
    

	public MySlidingMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		//计算屏幕宽
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics displayMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(displayMetrics);
		widthPixls = displayMetrics.widthPixels;
	}
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		LinearLayout ll = (LinearLayout) this.getChildAt(0);
		mMenu =  (ViewGroup) ll.getChildAt(0);
		mContent = (ViewGroup) ll.getChildAt(1);
		//如果设置最小值
		mMenuWidth =  (int) (0.8*widthPixls);
		mMenu.getLayoutParams().width = (int)(0.8*widthPixls);
		mContent.getLayoutParams().width = widthPixls;
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
		if(changed){
			this.scrollTo(mMenuWidth, 0);
			Log.i("INFO", "mMenuWidth:"+mMenuWidth);
		}
	}
	
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		// TODO Auto-generated method stub
		//l的变化是0~mMenuWidth
		float scale = (float)l/mMenuWidth;
		//scale的变化是0~1

        float leftScale = 1-scale*0.3f;
        //leftScale变化1~0.7
        ViewHelper.setScaleX(mMenu, leftScale);
        ViewHelper.setScaleY(mMenu, leftScale);

        //透明度变化1~0.3
        float leftAlpha = 1-scale*0.7f;
        ViewHelper.setAlpha(mMenu, leftAlpha);

        //位移变化
        ViewHelper.setTranslationX(mMenu, l*0.7f);

        //mContent变化 0.8~1.0
        float rightScale = 0.8f + 0.2f * scale;
        if (rightScale>1f) rightScale = 1f;
        ViewHelper.setScaleX(mContent, rightScale);
        ViewHelper.setScaleY(mContent, rightScale);

		super.onScrollChanged(l, t, oldl, oldt);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				break;
			case MotionEvent.ACTION_MOVE:
				break;
			case MotionEvent.ACTION_UP:
                int distance = getScrollX();
                Message message = new Message();
                message.what = MOVE;
                message.obj = distance;
                mHandler.sendMessage(message);

                break;
		}
		return super.onTouchEvent(ev);
	}


}
