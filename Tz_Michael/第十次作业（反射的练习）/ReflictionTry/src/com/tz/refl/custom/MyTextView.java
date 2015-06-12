package com.tz.refl.custom;

import com.tz.refl.constants.MyApplication;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyTextView extends TextView {

	public MyTextView(Context context) {
		this(context,null);
	}

	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setTypeface(MyApplication.typeFace);
	}

}
