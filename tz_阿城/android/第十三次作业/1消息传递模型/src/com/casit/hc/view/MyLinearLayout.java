package com.casit.hc.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyLinearLayout extends LinearLayout {

	public MyLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
	}
     @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {//秘书的事件
    	// TODO Auto-generated method stub
    	 return true;
 //	return super.onInterceptTouchEvent(ev);
    }
}
