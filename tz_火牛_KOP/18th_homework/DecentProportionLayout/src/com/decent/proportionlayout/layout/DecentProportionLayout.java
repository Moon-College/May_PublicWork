package com.decent.proportionlayout.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.decent.decentutil.DecentLogUtil;
import com.decent.proportionlayout.R;

public class DecentProportionLayout extends ViewGroup {

	private String TAG = "DecentProportionLayout";

	public DecentProportionLayout(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public DecentProportionLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public DecentProportionLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		// TODO Auto-generated method stub
		LayoutParams lp = new LayoutParams(getContext(), attrs);
		return lp;
	}

	@Override
	protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
		// TODO Auto-generated method stub
		LayoutParams lp = new LayoutParams(p);
		return lp;
	}

	@Override
	protected LayoutParams generateDefaultLayoutParams() {
		// TODO Auto-generated method stub
		return new LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.FILL_PARENT);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		// super.layout(l, t, r, b);
		int childCount = this.getChildCount();
		/*
		 * 遍历这一层的child
		 */
		for (int i = 0; i < childCount; i++) {
			View child = this.getChildAt(i);
			/*
			 * 如果visibility属性不是GONE，则开始layout
			 */
			if (child.getVisibility() != View.GONE) {
				LayoutParams lp = (LayoutParams) child.getLayoutParams();

				int childLeft;
				int childTop;
				int childRight;
				int childBottom;
				/*
				 * beasOnHalf打开和不打开处理不一样
				 */
				if (lp.beasOnHalf) {
					/*
					 * 打开beasOnHalf的时候childLeft和childTop的坐标需要减去自身宽度和长度的一半
					 * childRight和childBottom的坐标则在新的childLeft和childTop的基础上计算
					 */
					int halfChildWidthOffset = child.getMeasuredWidth() / 2;
					int halfChildHeightOffset = child.getMeasuredHeight() / 2;
					childLeft = (int) (lp.px * this.getMeasuredWidth()
							+ this.getPaddingLeft() - halfChildWidthOffset);
					childTop = (int) (lp.py * this.getMeasuredHeight()
							+ this.getPaddingTop() - halfChildHeightOffset);
					childRight = (int) (childLeft + child.getMeasuredWidth());
					childBottom = (int) (childTop + child.getMeasuredHeight());

				} else {
					/*
					 * 没打开的时候则正常通过比例px，py和padding进行计算
					 */
					childLeft = (int) (lp.px * this.getMeasuredWidth() + this
							.getPaddingLeft());
					childTop = (int) (lp.py * this.getMeasuredHeight() + this
							.getPaddingTop());
					childRight = (int) (childLeft + child.getMeasuredWidth());
					childBottom = (int) (childTop + child.getMeasuredHeight());
				}
				child.layout(childLeft, childTop, childRight, childBottom);
				DecentLogUtil.d(TAG, "i=" + i + ",childLeft=" + childLeft
						+ ",childTop" + childTop + ",lp.py=" + lp.py);
			}
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		measureChildren(widthMeasureSpec, heightMeasureSpec);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	public static class LayoutParams extends ViewGroup.LayoutParams {

		/**
		 * 控件在屏幕中x轴的比例
		 */
		public float px = 0;
		/**
		 * 控件在屏幕中y轴的比例
		 */
		public float py = 0;

		/**
		 * 控件在按照比例layout计算坐标的时候是否按照自身长度和宽度的一半来计算
		 */
		public boolean beasOnHalf = false;

		public LayoutParams(Context context, AttributeSet attributeSet) {
			super(context, attributeSet);
			/*
			 * 解析attributeSet里面的自定义一的三个参数
			 */
			TypedArray ta = context.obtainStyledAttributes(attributeSet,
					R.styleable.ProportionAttr);
			this.px = ta.getFloat(R.styleable.ProportionAttr_px, 0);
			this.py = ta.getFloat(R.styleable.ProportionAttr_py, 0);
			this.beasOnHalf = ta.getBoolean(
					R.styleable.ProportionAttr_baseonhalf, false);
			/*
			 * 需要显式的回收
			 */
			ta.recycle();
			// TODO Auto-generated constructor stub
		}

		/*
		 * 这两个参数是width和height
		 */
		public LayoutParams(int arg0, int arg1) {
			super(arg0, arg1);
			// TODO Auto-generated constructor stub
		}

		public LayoutParams(android.view.ViewGroup.LayoutParams arg0) {
			super(arg0);
			// TODO Auto-generated constructor stub
		}

	}
}
