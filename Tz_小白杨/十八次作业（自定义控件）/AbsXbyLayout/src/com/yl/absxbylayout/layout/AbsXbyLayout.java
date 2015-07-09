package com.yl.absxbylayout.layout;

import com.yl.layout.absxbylayout.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class AbsXbyLayout extends ViewGroup {

	public AbsXbyLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		Log.i("INFO", "AbsXbyLayout-1");
	}

	public AbsXbyLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		Log.i("INFO", "AbsXbyLayout-2");
	}

	public AbsXbyLayout(Context context) {
		super(context);
		Log.i("INFO", "AbsXbyLayout-3");
	}

	// 传父控件的参数
	@Override
	public android.view.ViewGroup.LayoutParams generateLayoutParams(
			AttributeSet attrs) {
		// TODO Auto-generated method stub
		Log.i("INFO", "generateLayoutParams-1");
		return new AbsXbyLayout.LayoutParams(getContext(), attrs);
	}

	// 传父控件的参数
	@Override
	protected android.view.ViewGroup.LayoutParams generateLayoutParams(
			android.view.ViewGroup.LayoutParams p) {
		// TODO Auto-generated method stub
		Log.i("INFO", "generateLayoutParams-2");
		return new AbsXbyLayout.LayoutParams(p);
	}

	// 传父控件的参数
	@Override
	protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
		// TODO Auto-generated method stub
		Log.i("INFO", "generateLayoutParams-3");
		return new AbsXbyLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 0, 0);
	}

	// 设置子控件的位置
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int count = getChildCount();
		Log.i("INFO", "onLayout");
		for (int i = 0; i < count; i++) {
			View view = this.getChildAt(i);
			if (view.getVisibility() != GONE) {
				LayoutParams lp = (LayoutParams) view.getLayoutParams();
				int left = (int) (lp.px * this.getMeasuredWidth() + view
						.getPaddingLeft());
				int top = (int) (lp.py * this.getMeasuredHeight() + view
						.getPaddingTop());
				int right = left + view.getMeasuredWidth();
				int bottom = top + view.getMeasuredHeight();
				view.layout(left, top, right, bottom);
			}
		}

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		Log.i("INFO", "onMeasure");
		this.measureChildren(widthMeasureSpec, heightMeasureSpec);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	// 把子控件（xml）参数传进来
	public static class LayoutParams extends ViewGroup.LayoutParams {
		private float px, py;

		// 解释xml把参数传进来
		public LayoutParams(Context arg0, AttributeSet arg1) {
			super(arg0, arg1);
			Log.i("INFO", "LayoutParams.LayoutParams1");
			TypedArray ta = arg0.obtainStyledAttributes(arg1,
					R.styleable.absxbylayout);
			this.px = ta.getFloat(R.styleable.absxbylayout_px, 0);
			this.py = ta.getFloat(R.styleable.absxbylayout_py, 0);
			ta.recycle();
		}

		// 利用动态代码的构造函数传进来
		public LayoutParams(int arg0, int arg1, float px, float py) {
			super(arg0, arg1);
			Log.i("INFO", "LayoutParams.LayoutParams2");
			this.px = px;
			this.py = py;
		}

		public LayoutParams(android.view.ViewGroup.LayoutParams arg0) {
			super(arg0);
			Log.i("INFO", "LayoutParams.LayoutParams3");
		}

	}

}
