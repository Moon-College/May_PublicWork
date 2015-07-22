package com.casit.hc.absmylayout;

import com.casit.hc.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;

public class AbsMyLayout extends ViewGroup {



	public AbsMyLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public AbsMyLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		Log.i("INFO", "AbsMyLayout");
		// TODO Auto-generated constructor stub
	}

	public AbsMyLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		Log.i("INFO", "onlayout");
		int count = getChildCount();

		for(int i = 0 ; i<count; i++){
			View child = getChildAt(i);
			if(child.getVisibility()!= GONE){
				AbsMyLayout.LayoutParams abslp= (LayoutParams) child.getLayoutParams();
				
				int left = (int) (this.getMeasuredWidth()*abslp.px +this.getPaddingLeft());
				int top =  (int) (this.getMeasuredHeight()*abslp.py +this.getPaddingTop());
				int right = left +child.getMeasuredWidth();
				int bottom = top +child.getMeasuredHeight();
				child.layout(left, top, right, bottom);
			}
		}
	}
	@Override
	public android.view.ViewGroup.LayoutParams generateLayoutParams(
			AttributeSet attrs) {
		// TODO Auto-generated method stub
		Log.i("INFO", "generateLayoutParams");
		return new AbsMyLayout.LayoutParams(getContext(), attrs);
	}
	
	@Override
	protected android.view.ViewGroup.LayoutParams generateLayoutParams(
			android.view.ViewGroup.LayoutParams p) {
		// TODO Auto-generated method stub
		return new AbsMyLayout.LayoutParams(p);
	}
	@Override
	protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
		// TODO Auto-generated method stub
		return new AbsMyLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 0);
	}

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    	// TODO Auto-generated method stub
    	measureChildren(widthMeasureSpec, heightMeasureSpec);
    	super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    
    public static class LayoutParams extends ViewGroup.LayoutParams{

    	public float px;
    	public float py;
		public LayoutParams(Context arg0, AttributeSet arg1) {
			super(arg0, arg1);
			Log.i("INFO", "layoutparams");
            TypedArray a = arg0.obtainStyledAttributes(arg1,
                    R.styleable.abshclayout);
           this.px = a.getFloat(R.styleable.abshclayout_px, 0);
           this.py = a.getFloat(R.styleable.abshclayout_py, 0);
            a.recycle();
			// TODO Auto-generated constructor stub
		}

		public LayoutParams(int arg0, int arg1, int px, int py) {
			super(arg0, arg1);
			this.px = px;
			this.py = py;
			// TODO Auto-generated constructor stub
		}

		public LayoutParams(android.view.ViewGroup.LayoutParams arg0) {
			super(arg0);
			// TODO Auto-generated constructor stub
		}
    	
    }
	
	

}
