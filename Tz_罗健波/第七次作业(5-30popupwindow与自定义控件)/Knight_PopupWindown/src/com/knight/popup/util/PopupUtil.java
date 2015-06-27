package com.knight.popup.util;

import com.knight.popupwindown.R;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

public class PopupUtil {
	private static Context context;
	private View view;
	private InputMethodManager imm;
	private boolean textChanged = false;
	private KeyEvent unKnowEvent = new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_UNKNOWN);
	private String commString = "";
	private LayoutInflater inflater;
	private PopupWindow popupWindow;
	private EditText popupedt;
	private Button popupBtn;
	public PopupUtil(Context context){
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
	}
	
	public void getPopup(){
		WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		int width = manager.getDefaultDisplay().getWidth();
		int height = manager.getDefaultDisplay().getHeight();
		popupWindow = new PopupWindow(context);
		view = inflater.inflate(R.layout.popup, null);
		popupWindow.setContentView(view);
		popupWindow.setWidth(width);
		popupWindow.setHeight(height * 1/8);
		popupWindow.setInputMethodMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		popupWindow.setBackgroundDrawable(context.getResources().getDrawable(android.R.color.background_light));
		popupWindow.setFocusable(true);
		popupedt = (EditText) view.findViewById(R.id.popupedt);
		popupBtn = (Button) view.findViewById(R.id.popupbtn);
		popupedt.setFocusable(true);
		popupedt.requestFocus();
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
		popupedt.setOnKeyListener(new PopupListener());
		popupedt.addTextChangedListener(new MyWatcher(popupedt));
		popupBtn.setOnClickListener(new MyClickListener());
		
	}
	
	private class MyClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			imm.hideSoftInputFromWindow(popupedt.getWindowToken(), 0);
			popupedt.setText("");
		}
		
	}
	
	private class PopupListener implements OnKeyListener {

		@Override
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			commString = ((EditText) v).getText().toString();
			return false;
		}
	}
	
	private class MyWatcher implements TextWatcher {
		private EditText editText;
		public MyWatcher(EditText editText){
			this.editText = editText;
		}
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			if (!textChanged) {
				editText.dispatchKeyEvent(unKnowEvent);
			}
		}

		@Override
		public void afterTextChanged(Editable s) {
			
		}
		
	}
}
