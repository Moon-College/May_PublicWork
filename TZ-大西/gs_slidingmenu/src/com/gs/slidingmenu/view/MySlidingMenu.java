package com.gs.slidingmenu.view;

import com.nineoldandroids.view.ViewHelper;

import android.content.Context;
import android.text.style.LineHeightSpan.WithDensity;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class MySlidingMenu extends HorizontalScrollView {
    private int widthPixls;
	private int mMenuWidth;
	//private int mContentWidth;
	private ViewGroup mMenu;
	private ViewGroup mContent;
    private final float mMenuWidthOut = 0.2f;
	
	public MySlidingMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		//计算屏幕宽
		WindowManager vm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics displayMetrics = new DisplayMetrics();
		vm.getDefaultDisplay().getMetrics(displayMetrics);
		widthPixls = displayMetrics.widthPixels;
		
	}
	
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		//测量
		LinearLayout ll = (LinearLayout) this.getChildAt(0);
		mMenu = (ViewGroup) ll.getChildAt(0);
		mContent = (ViewGroup) ll.getChildAt(1);
		mMenuWidth = (int) (0.8*widthPixls);
		mMenu.getLayoutParams().width = (int) (0.8*widthPixls);
		mContent.getLayoutParams().width = widthPixls;
		
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
		if(changed){
		    this.scrollTo(mMenuWidth, 0);
		}
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		// TODO Auto-generated method stub
		//scale 变化0-1
		float scale = (float)l/mMenuWidth;
		//0-0.7
		float leftscale = 1 - scale*0.3f;
		
		//缩小
		ViewHelper.setScaleX(mMenu, leftscale);
		ViewHelper.setScaleY(mMenu, leftscale);
		
		//位移变化
		ViewHelper.setTranslationX(mMenu, l*0.8f);
		
		//透明度 1~0.3
		float leftAlpha = 1 - scale*0.7f;
		ViewHelper.setAlpha(mMenu, leftAlpha);
		
		//mContent变化 0.8~1.0
		float rightScale = 0.8f+mMenuWidthOut*scale;
		ViewHelper.setScaleX(mContent, rightScale);
		ViewHelper.setScaleY(mContent, rightScale);
		
		super.onScrollChanged(l, t, oldl, oldt);
	}
	
	 

}
