package com.zjm.viewgroup;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class AbsCustomLayout extends ViewGroup {

	public AbsCustomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		Log.i("INFO", "调用AbsCustomLayout(Context context, AttributeSet attrs, int defStyleAttr)");
	}

	public AbsCustomLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		Log.i("INFO", "调用AbsCustomLayout(Context context, AttributeSet attrs)");
	}

	public AbsCustomLayout(Context context) {
		super(context);
		Log.i("INFO", "调用AbsCustomLayout(Context context) ");
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//无法支持wrap_content
		measureChildren(widthMeasureSpec, heightMeasureSpec);//测量子控件宽高
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int count = getChildCount();//子控件的数量
		for (int i = 0; i < count; i++) {
			View child = getChildAt(i);
			if(child.getVisibility() != GONE){
				AbsCustomLayout.LayoutParams layout = (LayoutParams) child.getLayoutParams();
				int childLeft = (int) (layout.px*getMeasuredWidth()+getPaddingLeft());
				int childTop = (int) (layout.py*getMeasuredHeight()+getPaddingTop());
				int childRight = childLeft+child.getMeasuredWidth();
				int childBottom = childTop + child.getMeasuredHeight();
				child.layout(childLeft, childTop, childRight, childBottom);
			}
		}
	}
	
	
	
	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		Log.i("INFO", "调用generateLayoutParams(AttributeSet attrs)");
		return new AbsCustomLayout.LayoutParams(getContext(), attrs);
	}
	
	@Override
	protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
		Log.i("INFO", "调用generateLayoutParams(ViewGroup.LayoutParams p)");
		return new AbsCustomLayout.LayoutParams(p);
	}

	@Override
	protected LayoutParams generateDefaultLayoutParams() {
		Log.i("INFO", "调用generateDefaultLayoutParams()");
		return  new AbsCustomLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,0,0);
	}



	public static class LayoutParams extends ViewGroup.LayoutParams{
		float px,py;
		public LayoutParams(Context c, AttributeSet arg1) {
			super(c, arg1);
			Log.i("INFO", "解析自定义属性");
			TypedArray a = c.obtainStyledAttributes(arg1, R.styleable.abscustomlayout);
			px = a.getFloat(R.styleable.abscustomlayout_px, 0);
			py = a.getFloat(R.styleable.abscustomlayout_py, 0);
			a.recycle();
		}


		public LayoutParams(int arg0, int arg1) {
			super(arg0, arg1);
			this.px = 0;
			this.py = 0;
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

}
