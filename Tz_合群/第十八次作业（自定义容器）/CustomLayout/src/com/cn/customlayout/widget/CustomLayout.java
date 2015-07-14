package com.cn.customlayout.widget;

import com.cn.customlayout.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class CustomLayout extends ViewGroup {

	public CustomLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CustomLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public CustomLayout(Context context) {
		super(context);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		measureChildren(widthMeasureSpec, heightMeasureSpec);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int childrenCount = getChildCount();
		for (int i = 0; i < childrenCount; i++) {
			View view = getChildAt(i);
			if (view.getVisibility() != GONE) {
				CustomLayout.LayoutParams params = (CustomLayout.LayoutParams) view
						.getLayoutParams();
				int childLeft = (int) (params.px * this.getMeasuredWidth()
						+ this.getPaddingLeft() - (view.getMeasuredWidth() / 2));
				int childTop = (int) (params.py * this.getMeasuredHeight()
						+ this.getPaddingTop() - (view.getMeasuredHeight() / 2));
				int childRight = childLeft + view.getMeasuredWidth();
				int childBottom = childTop + view.getMeasuredHeight();
				view.layout(childLeft, childTop, childRight, childBottom);
			}
		}
	}

	@Override
	protected android.view.ViewGroup.LayoutParams generateLayoutParams(
			android.view.ViewGroup.LayoutParams mLayoutParams) {

		return new CustomLayout.LayoutParams(mLayoutParams);
	}

	@Override
	public android.view.ViewGroup.LayoutParams generateLayoutParams(
			AttributeSet attrs) {
		return new CustomLayout.LayoutParams(getContext(), attrs);
	}

	@Override
	protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
		// 默认Parameters
		return new CustomLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 0, 0);
	}

	public static class LayoutParams extends ViewGroup.MarginLayoutParams {
		float px;
		float py;

		public LayoutParams(Context mcontext, AttributeSet attrs) {
			super(mcontext, attrs);

			// 解析参数
			TypedArray ta = mcontext.obtainStyledAttributes(attrs,
					R.styleable.custom_layout);
			px = ta.getFloat(R.styleable.custom_layout_px, 0);
			py = ta.getFloat(R.styleable.custom_layout_py, 0);

			// 释放资源
			ta.recycle();
		}

		public LayoutParams(android.view.ViewGroup.LayoutParams params) {
			super(params);
		}

		public LayoutParams(int width, int height, float px, float py) {
			super(width, height);
			this.px = px;
			this.py = py;
		}
	}

}
