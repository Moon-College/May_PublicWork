package com.tz.refl.custom;

import com.tz.refl.constants.MyApplication;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class MyButton extends Button {

	public MyButton(Context context) {
		this(context,null);
	}

	public MyButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setTypeface(MyApplication.typeFace);
	}

}
