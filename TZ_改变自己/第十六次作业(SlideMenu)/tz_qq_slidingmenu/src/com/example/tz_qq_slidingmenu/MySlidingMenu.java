package com.example.tz_qq_slidingmenu;

import com.nineoldandroids.view.ViewHelper;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class MySlidingMenu extends HorizontalScrollView {

	private int widthPixels;
	private int heightPixels;
	private int mMenuWidth;
	private int mContentWidth;
	private ViewGroup mMenu;
	private ViewGroup mContent;
	private final float mMenuWidthOut = 0.2f;

	public MySlidingMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		// 计算屏幕的宽度
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics display = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(display);
		widthPixels = display.widthPixels;
		heightPixels = display.heightPixels;

	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		LinearLayout ll = (LinearLayout) this.getChildAt(0);
		mMenu = (ViewGroup) ll.getChildAt(0);
		mContent = (ViewGroup) ll.getChildAt(1);
		// 如果设置最小值
		mMenuWidth = (int) (0.8 * widthPixels);
		mMenu.getLayoutParams().width = (int) (0.8 * widthPixels);
		mContent.getLayoutParams().width = widthPixels;
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
		if (changed) {
			this.scrollTo(mMenuWidth, 0);
			Log.i("INFO", "mMenuWidth:" + mMenuWidth);
		}
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		float scale = (float) l / mMenuWidth;
		float leftScale = (float) (1 - scale * 0.3F);
		ViewHelper.setScaleX(mMenu, leftScale);
		ViewHelper.setScaleY(mMenu, leftScale);
		float leftAlpha = 1 - scale * 0.7f;
		ViewHelper.setAlpha(mMenu, leftAlpha);
		ViewHelper.setTranslationX(mMenu, l * 0.7f);
		// mContent变化 0.8~1.0
		float rightScale = 0.8f + 0.2f * scale;
		ViewHelper.setScaleX(mContent, rightScale);
		ViewHelper.setScaleY(mContent, rightScale);

		super.onScrollChanged(l, t, oldl, oldt);
	}

}
