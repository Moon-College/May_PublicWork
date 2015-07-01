package com.tz.michael.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.tz.michael.activity.R;
/**
 * 自定义容器和属性的练习
 * @author admin
 *
 */
public class ProportionLayout extends ViewGroup {

	public ProportionLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public ProportionLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ProportionLayout(Context context) {
		super(context);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//不支持内容包裹
		measureChildren(widthMeasureSpec, heightMeasureSpec);//测量子控件的宽高
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		for(int i=0;i<this.getChildCount();i++){
			View child=getChildAt(i);
			if(child.getVisibility()!=GONE){
				ProportionLayout.LayoutParams plp=(LayoutParams) child.getLayoutParams();
				//获取子控件相对于自己的属性值
				int left=(int) (plp.px*this.getMeasuredWidth()+this.getPaddingLeft());
				int top=(int) (plp.py*this.getMeasuredHeight()+this.getPaddingTop());
				int right=left+child.getMeasuredWidth();
				int bottom=top+child.getMeasuredHeight();
				child.layout(left, top, right, bottom);
			}
		}
	}

	/**
	 * 父容器通过该方法拿到xml中设置的子控件相对于自己的属性
	 */
	@Override
	public android.view.ViewGroup.LayoutParams generateLayoutParams(
			AttributeSet attrs) {
		return new ProportionLayout.LayoutParams(getContext(), attrs);
	}
	
	@Override
	protected android.view.ViewGroup.LayoutParams generateLayoutParams(
			android.view.ViewGroup.LayoutParams p) {
		return new ProportionLayout.LayoutParams(p);
	}
	
	/**
	 * 父容器通过该方法拿到子控件动态设置的相对于自己的属性
	 */
	@Override
	protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
		return new ProportionLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 0);
	}
	
	public static class LayoutParams extends ViewGroup.LayoutParams{

		private float px;
		private float py;
		
		/**
		 * xml中设置属性时的方法
		 * @param arg0
		 * @param arg1
		 */
		public LayoutParams(Context arg0, AttributeSet arg1) {
			super(arg0, arg1);
			//解析arg1，拿到px和py
			TypedArray a=arg0.obtainStyledAttributes(arg1, R.styleable.proportionlayout);
			this.px=a.getFloat(R.styleable.proportionlayout_px, 0);
			this.py=a.getFloat(R.styleable.proportionlayout_py, 0);
			a.recycle();
		}

		/**
		 * 动态代码设置属性时的方法
		 * @param arg0 
		 * @param arg1
		 * @param px
		 * @param py
		 */
		public LayoutParams(int arg0, int arg1,float px,float py) {
			super(arg0, arg1);
			this.px=px;
			this.py=py;
		}

		public LayoutParams(android.view.ViewGroup.LayoutParams arg0) {
			super(arg0);
		}
		
	}
	
}
