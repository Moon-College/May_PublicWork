package com.qq.slidingmenu.view;

import com.nineoldandroids.view.ViewHelper;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class MyHorizontalScrollView extends HorizontalScrollView {

	private int widthPixls;// 屏幕宽
	private int mMenuWidth;// 菜单宽度
	private int mContentWidth;// 内容宽

	private ViewGroup mMenu;
	private ViewGroup mContent;

	// 菜单与屏幕右侧的距离(dp)
	private int myMenuPaddingRight = 80;
	private boolean once = false;
	
//	private final float mMenuWidthOut = 0.2f;

	public MyHorizontalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);

		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		// 计算屏幕宽度
		DisplayMetrics dism = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(dism);
		widthPixls = dism.widthPixels;
		
		//将dp转成px
		myMenuPaddingRight = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, myMenuPaddingRight, context
						.getResources().getDisplayMetrics());
		Log.i("INFO", "myMenuPaddingRight:"+myMenuPaddingRight);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		if (!once ) {//使其只调用一次
			LinearLayout childLayout = (LinearLayout) this.getChildAt(0);// 获取子容器
			mMenu = (ViewGroup) childLayout.getChildAt(0);//菜单
			mContent = (ViewGroup) childLayout.getChildAt(1);//内容
//			mMenuWidth = mMenu.getLayoutParams().width = (int) (0.8*widthPixls);//设置菜单的宽度
//			Log.i("INFO", "mMenuWidth:"+mMenuWidth);
			mMenuWidth = mMenu.getLayoutParams().width = widthPixls - myMenuPaddingRight;
			Log.i("INFO", "widthPixls:"+widthPixls);
			Log.i("INFO", "mMenuWidth:"+mMenuWidth);
			mContentWidth = mContent.getLayoutParams().width = widthPixls;
			
			once = true;
		 }
	}

	/**
	 * 设置View的位置，首先，先将Menu隐藏（在eclipse中ScrollView的画面内容（非滚动条）正数表示向左移，向上移）
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		//如果视图开始变化
		if (changed) {
			this.scrollTo(mMenuWidth, 0);//向左滑动，相当于把右边的内容页拖到正中央，隐藏菜单
		}
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
//		Log.i("INFO", "改变left:"+l);
		//l的变化是从mMenuWidth-0
		float scale = (float)l/mMenuWidth;//变化范围 1-0
		
		//菜单的缩放比例
		float leftScale = 1.0f - scale * 0.3f;
		//缩放菜单 1-0.7
		ViewHelper.setScaleX(mMenu, leftScale);
		ViewHelper.setScaleY(mMenu, leftScale);
		//菜单的偏移量
		ViewHelper.setTranslationX(mMenu, l*0.8f);
		
		//菜单透明度0.3-1
		float leftAlpha = 1.0f-0.7f*scale;
		ViewHelper.setAlpha(mMenu, leftAlpha);
		
		//内容页的缩放比例 
		float rightScale = 0.7f + 0.3f * scale;
		//缩放内容页 1-0.7
		ViewHelper.setScaleX(mContent, rightScale);
		ViewHelper.setScaleY(mContent, rightScale);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		
		switch (ev.getAction()) {
		case MotionEvent.ACTION_UP:
			int scrollX = this.getScrollX();//移动的距离
			if(scrollX>=mMenuWidth/2){
				//内容页
				this.smoothScrollTo(mMenuWidth,0);//向左滑动展示内容
			}else{
				this.smoothScrollTo(0,0);//向右滑动展示菜单
			}
			return true;
		default:
			break;
		}
		return super.onTouchEvent(ev);
	}

}
