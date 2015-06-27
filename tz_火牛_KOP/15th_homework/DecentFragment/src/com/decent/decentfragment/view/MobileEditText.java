package com.decent.decentfragment.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

public class MobileEditText extends EditText {
	private OnNumberChangeListner mOnNumberChangeListner;
	private TextWatcher mTextWatcher = new TextWatcher() {

		/*
		 * 在onTextChanged里面去调用mOnNumberChangeListner
		 * 
		 * @see android.text.TextWatcher#onTextChanged(java.lang.CharSequence,
		 * int, int, int)
		 */
		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
             if(mOnNumberChangeListner != null){
            	 /*
            	  * 调用通过setOnNumberChangeListner设置进来的监听器，的onNumberChange
            	  * 传的是父亲对象的this
            	  */
            	 mOnNumberChangeListner.onNumberChange(MobileEditText.this);
             }
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}
	};

	public OnNumberChangeListner getOnNumberChangeListner() {
		return mOnNumberChangeListner;
	}

	public void setOnNumberChangeListner(
			OnNumberChangeListner onNumberChangeListner) {
		if (onNumberChangeListner != null) {
			this.addTextChangedListener(mTextWatcher);
			this.mOnNumberChangeListner = onNumberChangeListner;
		} else {
			this.removeTextChangedListener(mTextWatcher);
			this.mOnNumberChangeListner = null;
		}
	}

	public MobileEditText(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MobileEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MobileEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

}
