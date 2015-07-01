package com.example.lzqlayout.view;

import com.example.lzqlayout.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class LzqAbsoluteLayout extends ViewGroup {

	public LzqAbsoluteLayout(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public LzqAbsoluteLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public LzqAbsoluteLayout(Context context) {
		super(context);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {

		int childCount = this.getChildCount();

		for (int i = 0; i < childCount; i++) {
			View child = getChildAt(i);
			if (child.getVisibility() != GONE) {

				LzqAbsoluteLayout.LayoutPfarams layoutParams = (LayoutPfarams) child
						.getLayoutParams();
				int childLeft = (int) (getPaddingLeft() + layoutParams.px
						* getMeasuredWidth() - child.getMeasuredWidth() / 2);
				int childTop = (int) (getPaddingTop() + layoutParams.py
						* getMeasuredHeight() - child.getMeasuredHeight() / 2);
				int childRight = childLeft + child.getMeasuredWidth();
				int childBottom = childTop + child.getMeasuredHeight();
				child.layout(childLeft, childTop, childRight, childBottom);
			}
		}
	}

	/**
	 * 测量
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// 不支持 Warp_Content
		measureChildren(widthMeasureSpec, heightMeasureSpec);// 测量子控件

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		return new LayoutPfarams(getContext(), attrs);
	}

	@Override
	protected LayoutParams generateLayoutParams(LayoutParams p) {
		return new LayoutPfarams(p);
	}

	@Override
	protected LayoutParams generateDefaultLayoutParams() {
		return new LayoutPfarams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 0, 0);
	}

	public static class LayoutPfarams extends ViewGroup.LayoutParams {

		float px;
		float py;

		public LayoutPfarams(Context arg0, AttributeSet arg1) {
			super(arg0, arg1);

			TypedArray array = arg0.obtainStyledAttributes(arg1,
					R.styleable.lzqAbsoluteLayout);
			this.px = array.getFloat(
					R.styleable.lzqAbsoluteLayout_lzqAbsoluteLayout_px, 0);
			this.py = array.getFloat(
					R.styleable.lzqAbsoluteLayout_lzqAbsoluteLayout_py, 0);

			array.recycle();
		}

		public LayoutPfarams(int arg0, int arg1, float px, float py) {
			super(arg0, arg1);
			this.px = px;
			this.py = py;
		}

		public LayoutPfarams(LayoutParams arg0) {
			super(arg0);
		}

	}

}
