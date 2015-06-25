package com.dd.fragmentandcallback;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.EditText;

public class MyEditText extends EditText {

	private onInputListener inputListener;
	public onInputListener getOnInputListener(){
		return inputListener;
	}
	public void setOnInputListener(onInputListener inputListener){
		this.inputListener = inputListener;
	}
	public MyEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return super.onKeyDown(keyCode, event);
	}
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
			inputListener.onInput(this);
		return super.onKeyUp(keyCode, event);
	}
}
