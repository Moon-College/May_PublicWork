package cn.ysh.callback.myview;

import cn.ysh.listener.OnTextChangedListener;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

public class MyView extends EditText{
	
//	private OnTextChangedListener onTextChangedListener;

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}

	public void setOnTextChangedListener(OnTextChangedListener onTextChangedListener) {
//		this.onTextChangedListener = onTextChangedListener;
		onTextChangedListener.OnTextChanged(MyView.this);
		
	}


}
