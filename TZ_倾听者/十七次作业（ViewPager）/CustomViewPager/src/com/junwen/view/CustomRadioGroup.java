package com.junwen.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioGroup;

import com.junwen.util.June;
import com.junwen.util.Logs;

public class CustomRadioGroup extends RadioGroup{

	private int screenWidth; //屏幕宽度
	private int height;
	public CustomRadioGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		screenWidth = June.getScreenWidth(context);
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		View childAt = this.getChildAt(1);
		int[] widthAndHeightforView = June.getWidthAndHeightforView(childAt);
		for (int i = 0; i < this.getChildCount(); i++) {
			View item = this.getChildAt(i);
			item.getLayoutParams().width = screenWidth / 3;
			item.getLayoutParams().height = widthAndHeightforView[1];
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
}
