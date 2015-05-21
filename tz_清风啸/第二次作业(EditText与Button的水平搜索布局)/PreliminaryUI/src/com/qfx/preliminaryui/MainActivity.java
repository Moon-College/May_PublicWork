package com.qfx.preliminaryui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.HORIZONTAL);
		LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		ll.setLayoutParams(params);
		EditText et = new EditText(this);
		LayoutParams etParams = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, 1);
		et.setLayoutParams(etParams);
		Button btn = new Button(this);
		LayoutParams btnParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		btn.setLayoutParams(btnParams);
		btn.setText(R.string.search);
		ll.addView(et, 0);
		ll.addView(btn, 1);
		setContentView(ll);
	}
}
