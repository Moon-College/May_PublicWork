package com.jim.myscrollview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyLinearLayout extends LinearLayout {

	public MyLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 小秘书，根据事件的性质判断是否由当前控件处理
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		return super.onInterceptTouchEvent(ev);
	}

}
