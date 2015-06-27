package com.view.viewpagees;

import com.example.viewpagerradiogroup.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.widget.RadioButton;
import android.widget.RadioGroup.LayoutParams;

public class MyRadioButton extends RadioButton{

	public MyRadioButton(Context context) {
		super(context);
		setGravity(Gravity.CENTER);
		LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		setLayoutParams(lp);
	}
	
	public void setSize(int size) {
		setTextSize(size);
	}
	public void setColors(int id) {
		setTextColor(id);
	}
	public void setTextes(String str) {
		setText(str);
	}
	
	public void setBack(Drawable id) {
		setBackground(id);
	}
	
	public void setBtn(boolean need) {
		if (!need) {
			setButtonDrawable(null);
		}
	}
	
	public void setPaddings(int pd) {
		setPadding(pd, 0, pd, 0);
	}

}
