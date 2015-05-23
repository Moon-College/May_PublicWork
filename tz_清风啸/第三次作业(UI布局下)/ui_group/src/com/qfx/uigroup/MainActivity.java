package com.qfx.uigroup;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
		RelativeLayout rl = new RelativeLayout(this);
		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT, 
				ViewGroup.LayoutParams.FILL_PARENT);
		rl.setLayoutParams(params);
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.HORIZONTAL);
		RelativeLayout.LayoutParams llParams = new RelativeLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		llParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		ll.setLayoutParams(llParams);
		EditText et = new EditText(this);
		LinearLayout.LayoutParams etParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT, 
				LinearLayout.LayoutParams.WRAP_CONTENT,
				1);
		etParams.gravity = Gravity.CENTER_VERTICAL;
		et.setLayoutParams(etParams);
		et.setHint(R.string.et_hint_text);
		Button btn = new Button(this);
		LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT, 
				LinearLayout.LayoutParams.WRAP_CONTENT);
		btn.setLayoutParams(btnParams);
		btn.setText(R.string.search);
		btn.setPadding(20, 20, 20, 20);
		btn.setTextSize(18);
		ImageView iv = new ImageView(this);
		RelativeLayout.LayoutParams ivParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		ivParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		iv.setLayoutParams(ivParams);
		iv.setBackgroundResource(R.drawable.movie);
		ll.addView(et, 0);
		ll.addView(btn, 1);
		rl.addView(ll, 0);
		rl.addView(iv, 1);
		setContentView(rl);
	}
}
