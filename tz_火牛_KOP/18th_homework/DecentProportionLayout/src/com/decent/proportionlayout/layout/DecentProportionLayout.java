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
		 * ������һ���child
		 */
		for (int i = 0; i < childCount; i++) {
			View child = this.getChildAt(i);
			/*
			 * ���visibility���Բ���GONE����ʼlayout
			 */
			if (child.getVisibility() != View.GONE) {
				LayoutParams lp = (LayoutParams) child.getLayoutParams();

				int childLeft;
				int childTop;
				int childRight;
				int childBottom;
				/*
				 * beasOnHalf�򿪺Ͳ��򿪴���һ��
				 */
				if (lp.beasOnHalf) {
					/*
					 * ��beasOnHalf��ʱ��childLeft��childTop��������Ҫ��ȥ�����Ⱥͳ��ȵ�һ��
					 * childRight��childBottom�����������µ�childLeft��childTop�Ļ����ϼ���
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
					 * û�򿪵�ʱ��������ͨ������px��py��padding���м���
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
		 * �ؼ�����Ļ��x��ı���
		 */
		public float px = 0;
		/**
		 * �ؼ�����Ļ��y��ı���
		 */
		public float py = 0;

		/**
		 * �ؼ��ڰ��ձ���layout���������ʱ���Ƿ��������ȺͿ�ȵ�һ��������
		 */
		public boolean beasOnHalf = false;

		public LayoutParams(Context context, AttributeSet attributeSet) {
			super(context, attributeSet);
			/*
			 * ����attributeSet������Զ���һ����������
			 */
			TypedArray ta = context.obtainStyledAttributes(attributeSet,
					R.styleable.ProportionAttr);
			this.px = ta.getFloat(R.styleable.ProportionAttr_px, 0);
			this.py = ta.getFloat(R.styleable.ProportionAttr_py, 0);
			this.beasOnHalf = ta.getBoolean(
					R.styleable.ProportionAttr_baseonhalf, false);
			/*
			 * ��Ҫ��ʽ�Ļ���
			 */
			ta.recycle();
			// TODO Auto-generated constructor stub
		}

		/*
		 * ������������width��height
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
