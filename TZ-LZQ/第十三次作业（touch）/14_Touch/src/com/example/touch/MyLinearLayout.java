package com.example.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyLinearLayout extends LinearLayout {

	public MyLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}


	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return true;
	}

}
