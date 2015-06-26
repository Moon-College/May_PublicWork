package com.main;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyLinear extends LinearLayout{

	private boolean ll = true;
	public MyLinear(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return ll;
	}
	
	
	public void setIntercept(boolean vlaue) {
		ll = vlaue;
		MyLinear.this.invalidate();
	}
	
}
