package com.casit.hc.myview;

import com.casit.hc.mylistener.OnMyListener;

import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;
import android.widget.EditText;

public class MyEditText extends EditText {

	public MyEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	OnMyListener onMyListener;
	public OnMyListener getOnMyListener() {
		return onMyListener;
	}

	public void setOnMyListener(OnMyListener onMyListener) {
		this.onMyListener = onMyListener;
	}




	@Override
	protected void onTextChanged(CharSequence text, int start,
			int lengthBefore, int lengthAfter) {
		// TODO Auto-generated method stub
		if(text!=null||text.length()!=0){
			if(onMyListener!=null)
			onMyListener.onText(super.getText().toString().trim());
		}
		super.onTextChanged(text, start, lengthBefore, lengthAfter);
	}
}
