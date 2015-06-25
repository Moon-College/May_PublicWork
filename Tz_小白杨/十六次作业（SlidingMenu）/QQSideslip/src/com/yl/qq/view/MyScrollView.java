package com.yl.qq.view;

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

import com.nineoldandroids.view.ViewHelper;

public class MyScrollView extends HorizontalScrollView {
	private float scrollWidth;
	private int msWidth;
	private ViewGroup menu;
	private ViewGroup content;
	
	public MyScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		DisplayMetrics metrics = new DisplayMetrics();
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(metrics);
		scrollWidth = metrics.widthPixels;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		msWidth = (int) (scrollWidth * 0.75f);
		ViewGroup vg = (ViewGroup) this.getChildAt(0);
		menu = (ViewGroup) vg.getChildAt(0);
		content = (ViewGroup) vg.getChildAt(1);
		menu.getLayoutParams().width = msWidth;
		content.getLayoutParams().width = (int) (scrollWidth);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if (changed) {
			this.scrollTo(msWidth, 0);
		}
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		// 1~0范围 (float)是必须加的
		float num = (float) l / msWidth;
		// 移动 scrollWidth~0
		ViewHelper.setTranslationX(menu, l * 0.7f);
		// 缩放 范围0~1
		float scaleNum = 1 - num * 0.3f;
		ViewHelper.setScaleX(menu, scaleNum);
		ViewHelper.setScaleY(menu, scaleNum);
		// 渐变 范围0~1
		float alphaNum = 1 - num;
		ViewHelper.setAlpha(menu, alphaNum);

		// 缩放
		float cScaleNum = 0.8f + num * 0.2f;
		ViewHelper.setScaleX(content, cScaleNum);
		ViewHelper.setScaleY(content, cScaleNum);
		super.onScrollChanged(l, t, oldl, oldt);
	}
}
