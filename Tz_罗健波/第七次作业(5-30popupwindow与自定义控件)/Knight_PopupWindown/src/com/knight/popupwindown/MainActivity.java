package com.knight.popupwindown;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button popup;
	private LayoutInflater inflater;
	private int width, height;
	private String commString = "";
	private InputMethodManager imm;
	private boolean textChanged = false;
	private TextView tv;
	private KeyEvent unKnowEvent = new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_UNKNOWN);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		popup = (Button) findViewById(R.id.popup);
		tv = (TextView)findViewById(R.id.incpopu);
		inflater = this.getLayoutInflater();
		WindowManager manager = (WindowManager) this
				.getSystemService(Context.WINDOW_SERVICE);
		width = manager.getDefaultDisplay().getWidth();
		height = manager.getDefaultDisplay().getHeight();
		popup.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final PopupWindow popupWindow = new PopupWindow(MainActivity.this);
				View view = inflater.inflate(R.layout.popup, null);
				popupWindow.setContentView(view);
				popupWindow.setBackgroundDrawable(MainActivity.this
						.getResources().getDrawable(android.R.color.white));
				popupWindow.setFocusable(true);
				final EditText popupEdt = (EditText) view.findViewById(R.id.popupedt);
				Button popupBtn = (Button) view.findViewById(R.id.popupbtn);
				popupWindow.setWidth(width);
				popupWindow.setHeight(height * 1 / 8);
				popupEdt.setFocusable(true);
				popupEdt.requestFocus();
				imm = (InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
				popupEdt.setOnKeyListener(new OnKeyListener() {
					
					@Override
					public boolean onKey(View v, int keyCode, KeyEvent event) {
						commString = ((EditText)v).getText().toString();
						return false;
					}
				});
				popupEdt.addTextChangedListener(new Watcher(popupEdt));
				popupBtn.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						if (commString != null && commString.length()>0) {
							tv.setText(commString);
							imm = (InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
							imm.hideSoftInputFromWindow(popupEdt.getWindowToken(), 0);
							popupEdt.setText("");
							popupWindow.dismiss();
						}else {
							Toast.makeText(MainActivity.this, "输入内容不能为空!", Toast.LENGTH_LONG).show();
						}
					}
				});
				popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
				popupWindow.showAtLocation(popup, Gravity.BOTTOM, 0, 0);
			}
		});
	}
	private  class Watcher implements TextWatcher {
		private EditText editText;
		public Watcher(EditText editText) {
			this.editText = editText;
		}
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			if (!textChanged) {
				editText.dispatchKeyEvent(unKnowEvent);
			}
		}
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			
		}

		@Override
		public void afterTextChanged(Editable s) {
			
		}
		
	}
}
