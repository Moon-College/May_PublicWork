/**
 * Project Name:lsn17_slidingmenu
 * File Name:MySlidingMenu.java
 * Package Name:com.zht.slidingmenu.widget
 * Date:2015-6-25下午6:41:36
 * Copyright (c) 2015, shixi_hongtao@staff.sina.com.cn All Rights Reserved.
 *
 */

package com.zht.slidingmenu.widget;

import com.nineoldandroids.view.ViewHelper;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * ClassName:MySlidingMenu <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-6-25 下午6:41:36 <br/>
 * 
 * @author acer
 * @version
 * @since JDK 1.6
 * @see
 */
public class MySlidingMenu extends HorizontalScrollView {
	private int widthPixls;
	private int mMenuWidth;
	private int mContentWidth;
	private ViewGroup mMenu;
	private ViewGroup mContent;
	private final float mMenuWidthOut = 0.2f;

	public MySlidingMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		LinearLayout ll = (LinearLayout) this.getChildAt(0);
		// 计算屏幕宽
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(dm);
		widthPixls = dm.widthPixels;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		LinearLayout ll = (LinearLayout) this.getChildAt(0);
		mMenu = (ViewGroup) ll.getChildAt(0);
		mContent = (ViewGroup) ll.getChildAt(1);
		// 如果设置最小值
		mMenuWidth = (int) (0.8 * widthPixls);
		mMenu.getLayoutParams().width = (int) (0.8 * widthPixls);
		mContent.getLayoutParams().width = widthPixls;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if (changed) {
			this.scrollTo(mMenuWidth, 0);
			Log.i("INFO", "mMenuWidth:" + mMenuWidth);
		}
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);

		// TODO Auto-generated method stub
		// l的变化是0~mMenuWidth
		float scale = (float) l / mMenuWidth;
		// scale的变化是0~1
		float leftScale = 1 - scale * 0.3f;
		// leftScale变化1~0.7
		ViewHelper.setScaleX(mMenu, leftScale);
		ViewHelper.setScaleY(mMenu, leftScale);

		// 透明度变化1~0.3
		float leftAlpha = 1 - scale * 0.7f;
		ViewHelper.setAlpha(mMenu, leftAlpha);

		// 位移变化
		ViewHelper.setTranslationX(mMenu, l * 0.7f);

		// mContent变化 0.8~1.0
		float rightScale = 0.8f + 0.2f * scale;
		ViewHelper.setScaleX(mContent, rightScale);
		ViewHelper.setScaleY(mContent, rightScale);
		super.onScrollChanged(l, t, oldl, oldt);

	}
}
