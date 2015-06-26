package com.example.tz_key;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyViewGroup extends LinearLayout{

	public MyViewGroup(Context context) {
		super(context);
	}
	
	
	
	public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}



	public MyViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}



	//√ÿ È
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return false;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		return false;
	}

}
