package com.tz.michael.customview;

import com.tz.michael.custominterface.TextChangeListenner;

import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;
import android.widget.EditText;

public class MyEditText extends EditText {

	private TextChangeListenner tcl;
	
	public void setTcl(TextChangeListenner tcl) {
		this.tcl = tcl;
	}

	public MyEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public Editable getText() {
		tcl.onText(super.getText().toString().trim());
		return super.getText();
	}
	
	@Override
	protected void onTextChanged(CharSequence text, int start,
			int lengthBefore, int lengthAfter) {
		if(text!=null&&text.length()!=0){
			getText();
		}
		super.onTextChanged(text, start, lengthBefore, lengthAfter);
	}
	
}
