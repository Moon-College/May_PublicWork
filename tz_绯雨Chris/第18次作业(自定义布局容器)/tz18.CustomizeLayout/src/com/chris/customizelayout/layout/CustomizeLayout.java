package com.chris.customizelayout.layout;

import com.chris.customizelayout.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class CustomizeLayout extends ViewGroup
{

	public CustomizeLayout(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public CustomizeLayout(Context context)
	{
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	//在xml中没有设定属性的事后调用，后续动态JAVA代码布局
	@Override
	protected ViewGroup.LayoutParams generateDefaultLayoutParams()
	{
		return new CustomizeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 0);
	}
	
	@Override
	public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs)
	{
		// TODO Auto-generated method stub
		return new CustomizeLayout.LayoutParams(getContext(), attrs);
	}
	
	@Override
	protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p)
	{
		// TODO Auto-generated method stub
		return new CustomizeLayout.LayoutParams(p);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		measureChildren(widthMeasureSpec, heightMeasureSpec);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b)
	{
		int count = getChildCount();
		for(int i=0; i<count; i++)
		{
			View view = getChildAt(i);
			if(View.GONE != view.getVisibility())
			{
				CustomizeLayout.LayoutParams cusLP = (LayoutParams) view.getLayoutParams();
				int left = (int) (cusLP.px*this.getMeasuredWidth() + this.getPaddingLeft());
				int top = (int) (cusLP.py*this.getMeasuredHeight() + this.getPaddingTop());
				int right = left + this.getMeasuredWidth();
				int bottom = top + this.getMeasuredHeight();
				view.layout(left, top, right, bottom);
			}
		}
	}
	
	public static class LayoutParams extends ViewGroup.MarginLayoutParams
	{
		float px, py;
		
		public LayoutParams(Context c, AttributeSet attrs)
		{
			super(c, attrs);
			TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.customizelayout);
			this.px = a.getFloat(R.styleable.customizelayout_px, 0);
			setDataRange(this.px, 0f, 1f);
			this.py = a.getFloat(R.styleable.customizelayout_py, 0);
			setDataRange(this.py, 0f, 1f);
			a.recycle();
		}

		private void setDataRange(float data, float min, float max)
		{
			if(data < min)
			{
				data = min;
			}
			else if(data > max)
			{
				data = max;
			}
		}

		public LayoutParams(int width, int height, float px, float py)
		{
			super(width, height);
			this.px = px;
			this.py = py;
		}

		public LayoutParams(android.view.ViewGroup.LayoutParams source)
		{
			super(source);
			// TODO Auto-generated constructor stub
		}

		public LayoutParams(MarginLayoutParams source)
		{
			super(source);
			// TODO Auto-generated constructor stub
		}
		
	}

}
