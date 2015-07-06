package com.junwen.view;

import com.example.customattr.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.opengl.Visibility;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class CustomViewGroup extends ViewGroup{

	/**
	 * 当XML布局加载属性
	 * @param context
	 * @param attrs
	 */
	public CustomViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	/**
	 * 当XML布局加载属性
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public CustomViewGroup(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	/**
	 * 直接New的时候调用
	 * @param context
	 */
	public CustomViewGroup(Context context) {
		super(context);
	}
	
	/**
	 * XML里加载
	 */
	@Override
	public android.view.ViewGroup.LayoutParams generateLayoutParams(
			AttributeSet attrs) {
		return new CustomViewGroup.LayoutParams(getContext(), attrs);
	}
	
	/**
	 * 添加LayoutParams
	 */
	@Override
	protected android.view.ViewGroup.LayoutParams generateLayoutParams(
			android.view.ViewGroup.LayoutParams p) {
		return new CustomViewGroup.LayoutParams(p);
	}
	
	/**
	 * 默认的
	 */
	@Override
	protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
		return new CustomViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 0);
	}
	
	/**
	 * 解析子控件
	 * @author admi
	 *
	 */
	public static class LayoutParams extends ViewGroup.LayoutParams {

		float px,py;
		public LayoutParams(Context arg0, AttributeSet arg1) {
			super(arg0, arg1);
			TypedArray a = arg0.obtainStyledAttributes(arg1, R.styleable.view);
			this.px = a.getFloat(R.styleable.view_px, 0);
			this.py = a.getFloat(R.styleable.view_py, 0);
		}

		public LayoutParams(int arg0, int arg1,float px,float py) {
			super(arg0, arg1);
			this.px = px;
			this.py = py;
		}

		public LayoutParams(android.view.ViewGroup.LayoutParams arg0) {
			super(arg0);
		}
		
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		measureChildren(widthMeasureSpec, heightMeasureSpec);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int childCount = this.getChildCount();
		for (int i = 0; i < childCount; i++) {
			View view = this.getChildAt(i);
					if(view.getVisibility() != GONE ){
						CustomViewGroup.LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
						int viewLeft = (int) (layoutParams.px * this.getMeasuredWidth() + this.getPaddingLeft());
						int viewTop = (int) (layoutParams.py * this.getMeasuredHeight() + this.getPaddingTop());
						int viewRight = viewLeft + view.getMeasuredWidth();
						int viewButton = viewTop + view.getMeasuredHeight();
						view.layout(viewLeft, viewTop, viewRight, viewButton);
					}
;		}
		
	}

}
